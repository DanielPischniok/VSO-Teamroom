package de.pingpong.vsoteamroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.pingpong.vsoteamroom.entities.Artifact;

public interface ArtifactRepository extends JpaRepository<Artifact, Long> {

	@Query("SELECT a FROM Artifact a WHERE a.title LIKE LOWER(CONCAT('%',:filename, '%'))")
	List<Artifact> findArtifactByFilename(@Param("filename")String filename);
}
