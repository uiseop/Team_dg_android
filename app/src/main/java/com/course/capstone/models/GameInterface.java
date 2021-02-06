package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameInterface {
    @GET("api/v1/cross")
    Call <List<CrossInfo>> getAllCross();

//    @RestController
//    public class CrossController {
//
//        @Autowired
//        private final CrossService crossService;
//
//        public CrossController(CrossService crossService) {
//            this.crossService = crossService;
//        }
//
//        @RequestMapping(value = "api/v1/cross", method = {RequestMethod.GET})
//        public List<Cross> getAll(){
//            return crossService.getAllCross();
//        }
//
//        @PostMapping("api/v1/cross/add")
//        @ResponseStatus(value=HttpStatus.OK)
//        public Cross addCross(@RequestBody Cross cross){
//            return crossService.addCross(cross);
//        }

//        @DeleteMapping("api/v1/cross/delete/{id}")
//        public void removeCross(@PathVariable String id) {
//            crossService.removeCross(id);
//        }
//
//    }
}
