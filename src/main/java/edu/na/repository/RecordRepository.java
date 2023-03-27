package edu.na.repository;

import edu.na.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecordRepository extends JpaRepository<Record,Long> {
      @Query(value = "select id from records order by id desc limit 1",nativeQuery = true)
      Long getLatestRecordedRecordId();


}
