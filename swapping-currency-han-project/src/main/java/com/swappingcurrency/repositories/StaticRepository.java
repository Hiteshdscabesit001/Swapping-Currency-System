package com.swappingcurrency.repositories;

import com.swappingcurrency.models.StaticContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaticRepository extends JpaRepository<StaticContent, Long> {
    StaticContent findByKeyName(String keyName);
}
