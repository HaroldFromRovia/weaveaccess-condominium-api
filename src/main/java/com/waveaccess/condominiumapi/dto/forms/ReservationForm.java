package com.waveaccess.condominiumapi.dto.forms;

import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationForm {

    private Instant startTime;
    private Instant endTime;
    private Long resourceId;

}
