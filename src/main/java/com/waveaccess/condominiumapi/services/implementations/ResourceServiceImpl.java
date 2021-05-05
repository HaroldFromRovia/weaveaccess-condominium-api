package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.dto.forms.ResourceForm;
import com.waveaccess.condominiumapi.mappers.ResourceMapper;
import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.repositories.ResourceRepository;
import com.waveaccess.condominiumapi.services.interfaces.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    @Override
    public Page<Resource> getResources(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    @Override
    public Resource saveResource(ResourceForm resourceForm) {
        return resourceRepository.save(resourceMapper.formToResource(resourceForm));
    }

    @Override
    public Resource editResource(ResourceForm resourceForm, Long id) {
        Resource resource = findById(id);

//        resourceForm.setAuthor(null);
//        resourceForm.setReadAlready(false);
//        String imagePath = fileService.save(resourceForm.getImage());
//        resourceForm.setImage(null);
//
//        resourceMapper.updateBookFromDto(resourceForm, bookCandidate);
//        resource.setImagePath(imagePath);

        return resourceRepository.save(resource);
    }

    @Override
    public Resource findById(Long id) {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        return resourceOptional.orElseThrow(ResourceNotFoundException::new);
    }
}
