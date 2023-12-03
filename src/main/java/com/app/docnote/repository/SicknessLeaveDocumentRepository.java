package com.app.docnote.repository;

import com.app.docnote.model.entity.SicknessLeaveDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicknessLeaveDocumentRepository extends JpaRepository<SicknessLeaveDocument, Long> {
}
