package net.myapp.journalApp.service;

import net.myapp.journalApp.entity.JournalEntry;
import net.myapp.journalApp.entity.User;
import net.myapp.journalApp.repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@Component
public class journalEntryService {

    @Autowired
    private journalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;



    @Transactional
    public  void  saveEntry(JournalEntry journalEntry, String username ){
        try{
            User user= userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            //transactional used to revert the changes if all doesnot work
           // user.setUsername(null);
            userService.saveUser(user);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }
    public  void  saveEntry(JournalEntry journalEntry ){
        journalEntryRepository.save(journalEntry);

    }
    public List<JournalEntry> getAll(){
        return  journalEntryRepository.findAll();
    }
//    optional is a box basically data ho skta ya nhi ho skta
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id , String username){
        try{
            User user= userService.findByUsername(username);
            boolean flag = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(flag){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e){

            throw  new RuntimeException("An error occured ", e);
        }



    }

}
//controller --> service --> repository