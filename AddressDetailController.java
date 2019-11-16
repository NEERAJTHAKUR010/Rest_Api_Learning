package com.mgmt.employee.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mgmt.employee.model.AddressDetail;
import com.mgmt.employee.service.AddressDetailService;

@RestController
public class AddressDetailController {

  @Autowired
  private AddressDetailService addressDetailService;
  
  @GetMapping(value="/checkAddress")
  public String checkData() {
   return "Address";  
  }
  
  @PostMapping(value="/insertAddDetail", consumes="application/json", produces="application/json")
  public ResponseEntity<AddressDetail> insertAddressDetail(@RequestBody AddressDetail addressDetail){
    System.out.println("AddressDetail ::" + addressDetail);
    AddressDetail returnData = addressDetailService.saveMyAddressDetail(addressDetail);
    return new ResponseEntity<AddressDetail>(returnData, HttpStatus.OK);
  }
  
  @RequestMapping(value="findAddDetail/{id}", consumes="application/json", produces="application/json")
  public ResponseEntity<AddressDetail> fetchAddressDetailById(@PathVariable Integer id){
    System.out.println("id ::" + id);
    Optional<AddressDetail> retrunDataTemp = addressDetailService.findAddressDetailById(id);
    AddressDetail returnData = retrunDataTemp.get();
    return new ResponseEntity<AddressDetail>(returnData, HttpStatus.OK);
  }
  
  @RequestMapping(value="/findAddDetailByEmp/{empId}", consumes = {"application/json"}, produces = {"application/json"})
  public ResponseEntity<List<AddressDetail>> findAddressDetailByEmpId(@PathVariable("empId") Integer empId) {
    List<AddressDetail> addressDataByEmpId = addressDetailService.findAddressDetailByEmpId(empId);
    return new ResponseEntity<List<AddressDetail>>(addressDataByEmpId, HttpStatus.OK);
  }
  
  @GetMapping(value="/findAddDetailAll", consumes = {"application/json"}, produces = {"application/json"})
  public ResponseEntity<List<AddressDetail>> fetchAllAddressDetail(){
    Iterable<AddressDetail> addressDataTemp = addressDetailService.findAllAddressDetail();
    List<AddressDetail> addressData = new LinkedList<>();
    for(AddressDetail e: addressDataTemp) {
      addressData.add(e);
    }
    return new ResponseEntity<List<AddressDetail>>(addressData, HttpStatus.OK);
  }
  
  @PutMapping(value="/deleteAddDetail/{id}")
  public ResponseEntity<Void> deleteAddressDetailById(@PathVariable("id") Integer id) {
    addressDetailService.deleteAddressDetailById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
