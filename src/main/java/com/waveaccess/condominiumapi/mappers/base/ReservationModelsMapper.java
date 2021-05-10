package com.waveaccess.condominiumapi.mappers.base;

import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.models.User;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class ReservationModelsMapper {

    @Named("user-id-map")
    public Long map(User user) {
        return user.getId();
    }

    @Named("resource-id-map")
    public Long map(Resource resource) {
        return resource.getId();
    }
}
