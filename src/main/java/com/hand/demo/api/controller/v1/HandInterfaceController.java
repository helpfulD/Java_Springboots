package com.hand.demo.api.controller.v1;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.hand.demo.config.SwaggerTags;
import org.hzero.boot.interfaces.sdk.dto.RequestPayloadDTO;
import org.hzero.boot.interfaces.sdk.dto.ResponsePayloadDTO;
import org.hzero.boot.interfaces.sdk.invoke.InterfaceInvokeSdk;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * API 接口
 */
@Api(tags = SwaggerTags.INTERFACE_QUERY)
@RestController("handInterfaceController.v1")
@RequestMapping("/v1/{organizationId}/hand/interface")
public class HandInterfaceController extends BaseController {
    @Autowired
    private InterfaceInvokeSdk interfaceInvokeSdk;
    @ApiOperation(value = "调用销售订单查询接口")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/invoke")
    public ResponseEntity<ResponsePayloadDTO> invoke(@PathVariable long organizationId) {
        // TODO
        String namespace = "HZERO";
        String interfaceCode = "hzero-order-1653.soheader.query";
        String serverCode = "HZERO_ORDER_1653_QUERY";
        RequestPayloadDTO payload = new RequestPayloadDTO();
        payload.setMediaType("application/json");
        Map<String, String> map = new HashMap<String, String>();
        map.put("organizationId", String.valueOf(organizationId));
        payload.setPathVariableMap(map);

        ResponsePayloadDTO responsePayloadDTO = interfaceInvokeSdk.invoke(namespace,
                serverCode,
                interfaceCode,
                payload);

        return Results.success(responsePayloadDTO);
    }
}