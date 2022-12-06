package ru.itmo.lab_3.handlers;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.lab_3.entities.TableRow;
import ru.itmo.lab_3.repository.HitCheckDao;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@ManagedBean(eager = true)
@SessionScoped
@Getter
@Setter
public class TableRowStorage implements Serializable {
    @ManagedProperty("#{hitCheckDao}")
    private HitCheckDao hitCheckDao;
    private List<TableRow> tableRows;

    public void addNewTableRow(TableRow tableRow) {
        hitCheckDao.save(tableRow);
        tableRows = hitCheckDao.getAllBySessionId(getSessionId());
        Collections.reverse(tableRows);
    }

    public void clear() {
        hitCheckDao.clearBySessionId(getSessionId());
        tableRows = hitCheckDao.getAllBySessionId(getSessionId());
        Collections.reverse(tableRows);
    }

    public String getSessionId() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getId();
    }

    public List<TableRow> getAllTableRows() {
        tableRows = hitCheckDao.getAllBySessionId(getSessionId());
        Collections.reverse(tableRows);
        return tableRows;
    }
}
