package org.commu.freecommunity.borad.domain.repository;

import org.commu.freecommunity.borad.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}