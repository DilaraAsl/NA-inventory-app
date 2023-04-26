package edu.na.repository;

import edu.na.entity.Device;
import edu.na.entity.Record;
import edu.na.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query(value = "select id from records order by id desc limit 1", nativeQuery = true)
    Long getLatestRecordedRecordId();


    @Query(value = "SELECT r FROM Record r JOIN Device d ON r.device.id = d.id WHERE d.serialNumber = ?1 ORDER BY current_date")
    List<Record> retrieveDeviceRecords(String serialNumber);

    @Query(value = "select r from Record r join User u on r.user.id=u.id where u.user_name=?1")
    List<Record> retrieveUserRecords(String user_name);

    //      @Query(value = "select r from Record r join Device d on r.device.id=d.id where d.id=?1")
//      Record findRecordByDeviceId(Long id);
    @Query(value = "SELECT EXISTS(SELECT 1 FROM Records WHERE device_id = ?1)", nativeQuery = true)
    boolean deviceExistsInRecord(Long deviceId);
@Query(value = "SELECT u.user_name from records r join users u on r.last_update_user_id=u.id where r.id=?1",nativeQuery = true)
    List<String> findWhoUpdatedRecord(Long id);

    @Query(value = "SELECT u.user_name from records r join users u on r.last_update_user_id=u.id",nativeQuery = true)
    List<String> findListOfUsersWhoUpdatedRecord();
}