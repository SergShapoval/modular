package com.logger;

import com.logger.dto.LogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.logger.writer.ConsoleWriter;
import com.logger.writer.FileWriter;
import com.logger.writer.Writer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Objects;

@Aspect
@Component
public class LoggerAspect {
    private static final String FILE = "file";
    private static final String SPACE = " ";
    private ObjectWriter objectWriter;

    @Value("${writer.type}")
    private String writerType;

    @Autowired
    private ApplicationContext applicationContext;
    private Writer writer;

    @PostConstruct
    public void init() {
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        System.out.println("WRITER TYPE: " + writerType);
        if (writerType.equals(FILE)) {
            writer = applicationContext.getBean(FileWriter.class);
        } else {
            writer = applicationContext.getBean(ConsoleWriter.class);
        }

    }

    @Pointcut("within(com..controller..*)")
    protected void controller() {
    }

    @Pointcut("within(com..dao..*)")
    protected void dao() {
    }

    @Pointcut("within(com..service..*)")
    protected void service() {
    }


    @Around("controller()")
    public void logController(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder("-------------------------CONTROLLER-------------------------" + System.lineSeparator());
        stringBuilder.append("METHOD: " + joinPoint.getSignature().getName() + System.lineSeparator());
        stringBuilder.append("*****CONTROLLER DAO*****" + System.lineSeparator());
        stringBuilder.append(createLog(joinPoint.getArgs(), "CONTROLLER", "INPUT") + System.lineSeparator());
        stringBuilder.append("*****OUTPUT CONTROLLER*****" + System.lineSeparator());
        stringBuilder.append("METHOD: " + joinPoint.getSignature().getName() + System.lineSeparator());
        Object proceed = joinPoint.proceed();
        proceed = Objects.nonNull(proceed) ? proceed : "EMPTY OUTPUT";
        stringBuilder.append(createLog(proceed, "CONTROLLER", "OUTPUT") + System.lineSeparator());
        writer.write(stringBuilder.toString() + System.lineSeparator());
    }

    @Around("service()")
    public void logService(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder("-------------------------SERVICE-------------------------" + System.lineSeparator());
        stringBuilder.append("METHOD: " + joinPoint.getSignature().getName() + System.lineSeparator());
        stringBuilder.append("*****INPUT SERVICE*****" + System.lineSeparator());
        stringBuilder.append(createLog(joinPoint.getArgs(), "SERVICE", "INPUT") + System.lineSeparator());
        stringBuilder.append("*****OUTPUT SERVICE*****" + System.lineSeparator());
        stringBuilder.append("METHOD: " + joinPoint.getSignature().getName() + System.lineSeparator());
        Object proceed = joinPoint.proceed();
        proceed = Objects.nonNull(proceed) ? proceed : "EMPTY OUTPUT";
        stringBuilder.append(createLog(proceed, "SERVICE", "OUTPUT") + System.lineSeparator());
        writer.write(stringBuilder.toString() + System.lineSeparator());
    }


    @Around("dao()")
    public void logDao(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder("-------------------------DAO-------------------------" + System.lineSeparator());
        stringBuilder.append("METHOD: " + joinPoint.getSignature().getName() + System.lineSeparator());
        stringBuilder.append("*****INPUT DAO*****" + System.lineSeparator());
        stringBuilder.append(createLog(joinPoint.getArgs(), "DAO", "INPUT") + System.lineSeparator());
        stringBuilder.append("*****OUTPUT DAO*****" + System.lineSeparator());
        stringBuilder.append("METHOD: " + joinPoint.getSignature().getName() + System.lineSeparator());
        Object proceed = joinPoint.proceed();
        proceed = Objects.nonNull(proceed) ? proceed : "EMPTY OUTPUT";
        stringBuilder.append(createLog(proceed, "DAO", "OUTPUT") + System.lineSeparator());
        writer.write(stringBuilder.toString() + System.lineSeparator());

    }

    private String createLog(Object object, String layer, String io) {
        try {
            LogDTO logDTO = new LogDTO();
            logDTO.setDate(new Date());
            logDTO.setArguments(object);
            logDTO.setLayer(layer);
            logDTO.setIo(io);
            return objectWriter.writeValueAsString(logDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return SPACE;
    }
}
