package de.pingpong.vsoteamroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.pingpong.vsoteamroom.entities.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {

}
