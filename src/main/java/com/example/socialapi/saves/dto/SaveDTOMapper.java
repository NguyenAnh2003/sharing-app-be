package com.example.socialapi.saves.dto;

import com.example.socialapi.saves.Save;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class SaveDTOMapper implements Function<Save, SaveDTO> {
    @Override
    public SaveDTO apply(Save save) {
        return new SaveDTO(save.getId(),
                save.getUserId().toString(),
                save.getPostId());
    }
}
