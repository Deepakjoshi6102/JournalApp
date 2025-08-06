package net.myapp.journalApp.repository;

import net.myapp.journalApp.entity.JournalEntry;
import net.myapp.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username); // ✅ Correct
    void deleteByUsername(String username);

}
