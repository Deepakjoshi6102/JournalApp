package net.myapp.journalApp.repository;

import net.myapp.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface journalEntryRepository extends MongoRepository<JournalEntry , ObjectId
        > {
}
