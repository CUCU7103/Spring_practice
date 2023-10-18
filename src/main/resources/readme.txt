server.servlet.encoding.force=true
spring.h2.console.enabled=true  // h2 데이터 베이스 콘솔 활성화
spring.jpa.defer-datasource-initialization=true

## jpa 설정
## sql 디버깅 활성화
# 로깅을 통해서 query를 보여준다.
logging.level.org.hibernate.SQL=DEBUG

# sql쿼리의 가독성을 높여준다.
##spring.jpa.properties.hibernate.format_sql=true
#
## h2 DB url 고정하기
#spring.datasource.url=jdbc:h2:mem:testdb
#
# mariadb 설정하기
#spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
#spring.datasource.url=jdbc:mariadb://localhost:3306/firstproject
#spring.datasource.username=root
#spring.datasource.password=7103park
#
# 대상 파일로 데이터베이스를 초기화한다.
#spring.sql.init.data-locations= classpath:/data.sql
#always로 설정하면, 애플리케이션을 시작할 때마다 spring.sql.init.data-locations에
지정된 SQL 스크립트가 실행됩니다
#spring.sql.init.mode=always
#
create-drop 값은 애플리케이션 시작 시 데이터베이스 스키마를 생성하고,
애플리케이션 종료 시 해당 스키마를 삭제하도록 Hibernate에 지시합니다.
이 설정은 주로 개발 또는 테스트 환경에서 사용되며,
각 테스트 실행이나 애플리케이션 실행 사이에 데이터베이스를 깨끗한 상태로 유지하고자 할 때 유용합니다.
#spring.jpa.hibernate.ddl-auto=create-drop
#
#???? ??? ???? DDL ??
##spring.jpa.hibernate.ddl-auto=update

# 로그에 보여지는 sql쿼리의 가독성을 높여준다.
#spring.jpa.properties.hibernate.format_sql=true
# JPA ???? ???? SQL? ?????? ??
#spring.jpa.show-sql=true


server.servlet.encoding.force=true
spring.h2.console.enabled=true
spring.jpa.deferdatasource-initialization=true

# JPA ?? ??
# ??? ??? ?? ??
logging.level.org.hibernate.SQL=DEBUG
# ?? ?????
spring.jpa.properties.hibernate.format_sql=true
# ???? ? ????
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# DB URL ??
# ??? URL ???? ??
spring.datasource.generate-unique-name=false
# ?? URL ????
spring.datasource.url=jdbc:h2:mem:testdb
