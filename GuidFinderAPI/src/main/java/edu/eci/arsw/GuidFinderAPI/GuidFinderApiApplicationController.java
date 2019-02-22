package edu.eci.arsw.GuidFinderAPI;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuidFinderApiApplicationController {
	private static final String template = "Hello, %s!";
    private final int count = 0;
    private final Date date= new Date();

    @RequestMapping("/guidFinderApiApplication")
    public GuidFinderApiApplication guidFinderApiApplication(@RequestParam(value="Guid", defaultValue="d0692660-c39a-4d73-9496-d9df0c4ebdf3") String guid,@RequestParam(value="Count", defaultValue="0") int count ) {
        return new GuidFinderApiApplication(date,String.format(template, guid),count);
    }
    
    @RequestMapping(value="/guideFinder",method=RequestMethod.POST)
    public GuidFinderApiApplication guidFinder(@RequestParam(value="Guid", defaultValue="d0692660-c39a-4d73-9496-d9df0c4ebdf3") String guid,@RequestParam(value="Count", defaultValue="0") int count ) {
    	 return new GuidFinderApiApplication(date,String.format(template, guid),count);
    }
}
