package com.kais.cloud.apis;

import com.kais.cloud.entities.PayDTO;
import com.kais.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author kais
 * @date 2024.04.05. 23:23
 */
@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {

    /**
     * 新增一条支付相关流水记录
     * @param payDTO
     * @return
     */
    @PostMapping(value = "pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 按主键记录查询支付流水信息
     * @param id
     * @return
     */
    @GetMapping(value = "pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id);

    /**
     * openfeign天然支持负载均衡
     * @return
     */
    @GetMapping(value = "pay/get/info")
    public String myLoadBalance();

    /**
     * Resilience4j CircuitBreaker （断路器）的例子--服务熔断、降级
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子(服务隔离--信号量舱壁)
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);
}
