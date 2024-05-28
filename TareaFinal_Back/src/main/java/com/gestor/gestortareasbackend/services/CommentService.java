package com.gestor.gestortareasbackend.services;

import com.gestor.gestortareasbackend.model.task.Task;
import com.gestor.gestortareasbackend.model.comment.Comment;
import com.gestor.gestortareasbackend.model.comment.dto.RequestComment;
import com.gestor.gestortareasbackend.model.comment.dto.ResponseComment;
import com.gestor.gestortareasbackend.model.comment.dto.CommentResponseMapper;
import com.gestor.gestortareasbackend.repository.CommentRepository;
import com.gestor.gestortareasbackend.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentResponseMapper commentResponseMapper;

    @Transactional(readOnly = true)
    public Optional<ResponseComment> getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(commentResponseMapper::commentToResponseComment);
    }

    @Transactional(readOnly = true)
    public List<ResponseComment> getAllCommentsByTaskId(Long taskId) {
        return commentResponseMapper.commentsToResponseComments(commentRepository.findByTaskId(taskId));
    }

    @Transactional
    public ResponseComment createComment(RequestComment requestComment) {
        Task task = taskRepository.findById(requestComment.getTask().getId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + requestComment.getTask().getId()));

        Comment comment = Comment.builder()
                .content(requestComment.getContent())
                .task(task)
                .build();

        return commentResponseMapper.commentToResponseComment(commentRepository.save(comment));
    }

    @Transactional
    public Optional<ResponseComment> updateComment(Long id, RequestComment commentDetails) {
        final Optional<Comment> existingComment = commentRepository.findById(id);
        existingComment.ifPresent(comment -> {
            comment.setContent(commentDetails.getContent());
            commentRepository.save(comment);
        });
        return existingComment.map(commentResponseMapper::commentToResponseComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public boolean existsById(final Long id) {
        return commentRepository.existsById(id);
    }
}
