package demo.springboot.mapper;

import demo.springboot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 *@program: UserMapper
 *@description: Springboot+jpa测试mapper类
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //Spring Data JPA 可以根据接口方法名来实现数据库操作，
    // 主要的语法是 findXXBy、readAXXBy、queryXXBy、countXXBy、getXXBy 后面跟属性名称，
    // 利用这个功能仅需要在定义的 Repository 中添加对应的方法名即可，使用时 Spring Boot 会自动帮我们实现.
    //eg:
    //And   findByLastnameAndFirstname	… where x.lastname = ?1 and x.firstname = ?2
    //Or	findByLastnameOrFirstname	… where x.lastname = ?1 or x.firstname = ?2
    //Is，Equals	findByFirstnameIs，findByFirstnameEquals	… where x.firstname = ?1
    //Between	findByStartDateBetween	… where x.startDate between ?1 and ?2
    //LessThan	findByAgeLessThan	… where x.age < ?1
    //LessThanEqual	findByAgeLessThanEqual	… where x.age ⇐ ?1
    //GreaterThan	findByAgeGreaterThan	… where x.age > ?1
    //GreaterThanEqual	findByAgeGreaterThanEqual	… where x.age >= ?1
    //After	findByStartDateAfter	… where x.startDate > ?1
    //Before	findByStartDateBefore	… where x.startDate < ?1
    //IsNull	findByAgeIsNull	… where x.age is null
    //IsNotNull，NotNull	findByAge(Is)NotNull	… where x.age not null
    //Like	findByFirstnameLike	… where x.firstname like ?1
    //NotLike	findByFirstnameNotLike	… where x.firstname not like ?1
    //StartingWith	findByFirstnameStartingWith	… where x.firstname like ?1 (parameter bound with appended %)
    //EndingWith	findByFirstnameEndingWith	… where x.firstname like ?1 (parameter bound with prepended %)
    //Containing	findByFirstnameContaining	… where x.firstname like ?1 (parameter bound wrapped in %)
    //OrderBy	findByAgeOrderByLastnameDesc	… where x.age = ?1 order by x.lastname desc
    //Not	findByLastnameNot	… where x.lastname <> ?1
    //In	findByAgeIn(Collection ages)	… where x.age in ?1
    //NotIn	findByAgeNotIn(Collection age)	… where x.age not in ?1
    //TRUE	findByActiveTrue()	… where x.active = true
    //FALSE	findByActiveFalse()	… where x.active = false
    //IgnoreCase	findByFirstnameIgnoreCase	… where UPPER(x.firstame) = UPPER(?1)

    /***
     * 根据用户名或者邮件地址查找用户信息
     * @param userName
     * @param email
     * @return
     */
    User findByUserNameOrEmail(String userName, String email);

    /***
     * 根据用户名查找用户信息
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    @Query(value = "select * from user", nativeQuery = true)
    Page<User> findAll(Pageable pageable);

    @Query(value = "select * from user where nick_name = ?1",nativeQuery = true)
    Page<User> findByNickName(String nickName, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String  userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    @Override
    void deleteById(Long id);
}
