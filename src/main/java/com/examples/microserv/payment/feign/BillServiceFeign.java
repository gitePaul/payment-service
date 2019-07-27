package com.examples.microserv.payment.feign;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.examples.microserv.payment.dto.BillDto;

@FeignClient(name="billing-service")
@Component
public interface BillServiceFeign {
		
    @GetMapping("/bills")
    List<BillDto> getAllBills();
    
    @PostMapping(value = "/verification")
    BillDto getBillDetailBySpCodeAndBillControlNumber(@RequestBody BillDto billServiceDto);
    
    @PostMapping(value = "/settlement")
    BillDto updateBillDetailByPaidStatus(@RequestBody BillDto billServiceDto);
    
    @PostMapping(value = "/{billControlNumber}")
    BillDto getBillDetailByControlNumber(@PathVariable String billControlNumber); 

}