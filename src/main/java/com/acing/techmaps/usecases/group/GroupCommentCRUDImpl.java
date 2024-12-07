package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.GroupComment;
import com.acing.techmaps.usecases.group.gateway.GroupCommentDAO;
import com.acing.techmaps.web.model.group.request.GroupCommentRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupCommentCRUDImpl implements GroupCommentCRUD {
    private final GroupCommentDAO groupCommentDAO;

    public GroupCommentCRUDImpl(GroupCommentDAO groupCommentDAO) {
        this.groupCommentDAO = groupCommentDAO;
    }

    @Override
    public GroupComment create(GroupCommentRequest request) {
        return groupCommentDAO.add(request.toGroupComment());
    }

    @Override
    public GroupComment getById(UUID id) {
        return groupCommentDAO.findById(id);
    }

    @Override
    public List<GroupComment> getByGroupPostId(UUID postId) {
        return groupCommentDAO.findByGroupPostId(postId);
    }

    @Override
    public List<GroupComment> getByUserId(UUID userId) {
        return groupCommentDAO.findByUserId(userId);
    }

    @Override
    public GroupComment updateComment(GroupCommentRequest request, UUID id) {
        return groupCommentDAO.update(request.toGroupComment(), id);
    }

    @Override
    public void delete(UUID id) {
        GroupComment groupComment = groupCommentDAO.findById(id);
        groupCommentDAO.delete(groupComment.getId());
    }
}
