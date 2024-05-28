package com.gestor.gestortareasbackend.services;


import com.gestor.gestortareasbackend.model.tag.Tag;
import com.gestor.gestortareasbackend.model.tag.dto.RequestTag;
import com.gestor.gestortareasbackend.model.tag.dto.ResponseTag;
import com.gestor.gestortareasbackend.model.tag.dto.TagResponseMapper;
import com.gestor.gestortareasbackend.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagResponseMapper tagResponseMapper;

    public Optional<ResponseTag> getTagById(Long id) {
        final Optional<Tag> tagOptional = tagRepository.findById(id);
        return tagOptional.map(tagResponseMapper::tagToResponseTag);
    }

    @Transactional(readOnly = true)
    public List<ResponseTag> getAllTags() {
        return tagResponseMapper.tagsToResponseTags(tagRepository.findAll());
    }

    @Transactional
    public ResponseTag createTag(RequestTag requestTag) {
        final Tag tag = Tag.builder().name(requestTag.getName()).build();
        return tagResponseMapper.tagToResponseTag(tagRepository.save(tag));
    }

    public Optional<ResponseTag> updateTag(Long id, RequestTag tagDetails) {
        final Optional<Tag> existingTag = tagRepository.findById(id);
        existingTag.ifPresent(tag -> {
            tagResponseMapper.updateEntityFromDto(tagDetails, tag);
            tagRepository.save(tag);
        });
        return existingTag.map(tagResponseMapper::tagToResponseTag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    public boolean existsById(final Long id) {
        return tagRepository.existsById(id);
    }

    public List<ResponseTag> findByName(final String name) {
        final List<Tag> tags = tagRepository.findByNameContainingIgnoreCase(name);
        return tagResponseMapper.tagsToResponseTags(tags);
    }
}
