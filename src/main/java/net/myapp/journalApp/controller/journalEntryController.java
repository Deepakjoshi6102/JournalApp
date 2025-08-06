//package net.myapp.journalApp.controller;
//
//
//import net.myapp.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.*;
//
////Method inside a controller should be public so that they can be accessed and invoked by spring framework or external http request
//
//@RestController
//@RequestMapping("/_journal")
//public class journalEntryController {
//
//    private Map<Long, JournalEntry> journalEntries=new HashMap<>();
//
//    @GetMapping("/abc")
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry){
//          journalEntries.put(myEntry.getId(),myEntry);
//          return true;
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
//        return journalEntries.remove(myId);
//    }
//    @PutMapping("/update_id/{id}")
//    public JournalEntry updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry ){
//        return journalEntries.put(id,myEntry);
//    }
//
//}
