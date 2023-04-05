package edu.na.repository;

import edu.na.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Long> {
      @Query(value = "select id from records order by id desc limit 1",nativeQuery = true)
      Long getLatestRecordedRecordId();


      @Query(value = "SELECT r FROM Record r JOIN Device d ON r.device.id = d.id WHERE d.serialNumber = ?1 ORDER BY current_date")
      List<Record> retrieveDeviceRecords(String serialNumber);
      @Query(value = "select r from Record r join User u on r.user.id=u.id where u.user_name=?1")
      List<Record> retrieveUserRecords(String user_name);

}
