package com.killrvideo.service.user.dao;

import com.killrvideo.dse.dao.DaoSupport;
import com.killrvideo.service.user.dto.User;
import com.killrvideo.service.user.dto.UserCredentials;
import com.killrvideo.service.user.dto.UserCredentials_Table;
import com.killrvideo.service.user.dto.User_Table;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Handling user.
 *
 * @author DataStax Developer Advocates Team
 */
@Repository
public class UserDao extends DaoSupport {

    /** Logger for DAO. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    
    /** Data model constants. */
    public static final String TABLENAME_USERS                         = "users";
    public static final String TABLENAME_USER_CREDENTIALS              = "user_credentials";
    
    /**
     * Create user Asynchronously composing things. (with Mappers)
     * 
     * @param user
     *      user Management
     * @param hashedPassword
     *      hashed Password
     * @return
     */
    public CompletableFuture<Void> createUserAsync(User user, String hashedPassword) {
        
        String errMsg = String.format("Exception creating user because it already exists with email %s", user.getEmail());

        CompletableFuture<Boolean> insertUserCredentialsAsync = cqlTemplate.dsl()
                                                                         .withConsistency(
                                                                             ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                                                                         .insertInto(UserCredentials_Table.user_credentials)
                                                                         .set(UserCredentials_Table.email, user.getEmail())
                                                                         .set(UserCredentials_Table.password, hashedPassword)
                                                                         .set(UserCredentials_Table.userid, user.getUserid())
                                                                         .executeAsync();
        // Execute user creation only if credentials did no exist
        CompletableFuture<Boolean> insertUserAsync = insertUserCredentialsAsync.thenCompose(applied -> {
            if (applied) {
                return cqlTemplate.dsl()
                       .withConsistency(
                           ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                       .insertInto(User_Table.users)
                       .set(User_Table.userid, user.getUserid())
                       .set(User_Table.firstname, user.getFirstname())
                       .set(User_Table.lastname, user.getLastname())
                       .set(User_Table.email, user.getEmail())
                       .set(User_Table.createdAt, Instant.now())
                       .executeAsync();
            }
            return insertUserCredentialsAsync;
        });

        /**
         * ThenAccept in the same thread pool (not using thenAcceptAsync())
         */
        return insertUserAsync.thenAccept(applied -> {
            if (!applied) {
                LOGGER.error(errMsg);
                throw new CompletionException(errMsg, new IllegalArgumentException(errMsg));
            }
        });
    }

    /**
     * Get user Credentials 
     * @param email
     * @return
     */
    public CompletableFuture< UserCredentials > getUserCredentialAsync(String email) {
        return cqlTemplate.dsl().selectFrom(UserCredentials_Table.user_credentials)
                          .where(UserCredentials_Table.email.eq(email))
                          .fetchOneAsync()
                          .thenApply(record -> new UserCredentials(record.get(UserCredentials_Table.email),
                                                            record.get(UserCredentials_Table.password),
                                                            record.get(UserCredentials_Table.userid)));
    }
    
    /**
     * Retrieve user profiles.
     *
     * @param userids
     * @return
     */
    public CompletableFuture < List < User > > getUserProfilesAsync(List < UUID > userids) {
        return cqlTemplate.dsl().withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                          .selectFrom(User_Table.users)
                          .where(User_Table.userid.in(userids))
                          .fetchAsync().thenApply(records -> records.stream()
                                                                    .map(record -> new User(
                                                                        record.get(User_Table.userid),
                                                                        record.get(User_Table.firstname),
                                                                        record.get(User_Table.lastname),
                                                                        record.get(User_Table.email),
                                                                        record.get(User_Table.createdAt)
                                                                    )).collect(Collectors.toList()));
    }
}
