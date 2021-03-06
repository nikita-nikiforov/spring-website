package io.nikiforov.edu.dao;

import io.nikiforov.edu.entity.LectureFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureFileRepository extends JpaRepository<LectureFile, Integer> {
    List<LectureFile> findAllByLectureId(int id);

    List<LectureFile> findAllByLectureIdAndContentType(int lectureId, String contentType);
}
