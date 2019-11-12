**简介**
    
    **这是一个springboot+gradle+flyway+spring security+mysql+swagger2搭建的一个java应用**

*1 如何配置数据库*

    为了方便大家了解整个数据库配置过程中会通过spring加载哪些配置文件，我们这里就不使用默认配置的方式
    DataSourcePropertiesConfiguration 类中定义了所有和数据库配置相关的配置项，该类所做的事情以及类中的配置
    在DataSourceProperties这个类中其实完全已经实现。若需要用到数据库连接池，对应springboot也提供了相应的配置类
    HikariDataSource，我们只需要在config 文件application.yaml文件中按照规范配置即可。
       
    DataSourceConfiguration 定义了数据库配置中最核心对象DataSource如何注入，在这里我们使用了性能比较好的HikariCP数据库连接池来配置数据库连接

    数据库采用了mysql，在这里我们使用了docker的方式启动数据库，若之前未接触过docker，可以简单去了解下docker概念以及如何使用
    项目根目录下有一个docker-compose.yaml文件中定义了所有需要使用的docker资源
    启动命令：在根目录下执行 docker-compose up -d mysql 只启动mysql 服务，若要启动里面的所有服务 docker-compose up -d


*2 如何配置spring security*
    
    具体操作流程和概念理解可查看博客，博客地址 ****

*3 redis配置*
    
    用户登录时要将token存储起来，这里引入了redis，采用redis的哨兵模式部署redis
    具体配置文件 RedisPropertiesConfiguration RedisConfiguration

*4 配置swagger2*

    通过访问http://localhost:10000/swagger-ui.html可查看项目中所有的api*

*5 配置flyway *
    
    db/migration 目录下指定了所有和项目相关的数据库脚本文件*
    配置文件中配置 flyway:
              enabled: true 默认是开启的
              locations: db/migration 默认就在这个路径
          
*6 启动项目*
   
   ./gradlew bootRun 后期会更新加入多环境启动方式
