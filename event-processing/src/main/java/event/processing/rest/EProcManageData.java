package event.processing.rest;

import org.springframework.http.ResponseEntity;

import common.data.dto.DeviceDataDTO;

public interface EProcManageData {

    public ResponseEntity<String> receive(DeviceDataDTO deviceDataDTO);

}
