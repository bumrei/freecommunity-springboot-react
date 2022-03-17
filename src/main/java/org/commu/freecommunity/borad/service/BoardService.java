package org.commu.freecommunity.borad.service;
import org.commu.freecommunity.borad.domain.entity.BoardEntity;
import org.commu.freecommunity.borad.domain.repository.BoardRepository;
import org.commu.freecommunity.borad.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.OrderBy;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public List<BoardDto> getBoardlist() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for ( BoardEntity boardEntity : boardEntities) {
            BoardDto boardDTO = BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .writer(boardEntity.getWriter())
                    .createdDate(boardEntity.getCreatedDate())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;
    }
    @Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDTO;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }


}
