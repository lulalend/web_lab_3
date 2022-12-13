package ru.itmo.lab_3.entities;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.lab_3.handlers.HitChecker;
import ru.itmo.lab_3.handlers.TableRowStorage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ManagedBean
@RequestScoped
@Getter
@Setter
@Table(name = "hits")
@Entity
public class TableRow implements Serializable {
    @Transient
    private static final ZoneOffset UTC_TIMEZONE = ZoneOffset.of("+0");
    @Transient
    double startTime = System.nanoTime();
    @ManagedProperty("#{tableRowStorage}")
    @Transient
    TableRowStorage tableRowStorage;

    @ManagedProperty("#{hitChecker}")
    @Transient
    HitChecker hitChecker;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal r = new BigDecimal("1");
    private BigDecimal x;
    private BigDecimal y;
    private boolean hitResult;
    private OffsetDateTime clientDate = OffsetDateTime.now(UTC_TIMEZONE);
    private BigDecimal executionTime;
    private String sessionId;

    public void handleRequest() {
        hitResult = hitChecker.checkHit(r, x, y);
        //in millisecond
        executionTime = BigDecimal.valueOf((System.nanoTime()-startTime)/1000000d)
                .setScale(2, RoundingMode.DOWN);
        sessionId = getSessionId();
        tableRowStorage.addNewTableRow(this);
    }

    public String getSessionId() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getId();
    }

    @Override
    public String toString() {
        return "TableRow{" +
                "r=" + r +
                ", x=" + x +
                ", y=" + y +
                ", resultHit='" + hitResult + '\'' +
                ", clientDate=" + clientDate +
                ", executionTime=" + executionTime +
                '}';
    }
}
