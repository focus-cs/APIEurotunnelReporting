/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eurotunnel.sciforma;

import com.eurotunnel.sciforma.beans.Connector;
import com.eurotunnel.sciforma.beans.SciformaField;
import com.eurotunnel.sciforma.manager.ProjectManager;
import com.eurotunnel.sciforma.manager.ProjectManagerImpl;
import com.sciforma.psnext.api.CostData;
import com.sciforma.psnext.api.DataViewRow;
import com.sciforma.psnext.api.DatedData;
import com.sciforma.psnext.api.DoubleDatedData;
import com.sciforma.psnext.api.Global;
import com.sciforma.psnext.api.InvalidDataException;
import com.sciforma.psnext.api.LockException;
import com.sciforma.psnext.api.PSException;
import com.sciforma.psnext.api.Project;
import com.sciforma.psnext.api.ResAssignment;
import com.sciforma.psnext.api.Resource;
import com.sciforma.psnext.api.Session;
import com.sciforma.psnext.api.StringDatedData;
import com.sciforma.psnext.api.Task;
import com.sciforma.psnext.api.TaskOutlineList;
import com.sciforma.psnext.api.Timesheet;
import com.sciforma.psnext.api.TimesheetAssignment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author ERIC
 */
public class Run {

    public static ApplicationContext ctx;

    private static String IP;
    private static String PORT;
    private static String CONTEXTE;
    private static String USER;
    private static String PWD;

    public static Session mSession;
    private static List<Project> projectList = null;
    private static List<Resource> listResource = null;
    public static Hashtable<String, ArrayList<String>> projects;
    private static ProjectManager projectManager;

    private static String VDD_PROJET_BUDGET;
    private static String VDD_PROJET_BUDGET_TYPE;
    private static String VDD_PROJET_BUDGET_CODE;
    private static String VDD_PROJET_BUDGET_REALISE_ACTUAL;
    private static String VDD_PROJET_BUDGET_ENGAGE_COMMANDE;
    private static String VDD_PROJET_BUDGET_DESCRIPTION;
    private static String VDD_PROJET_BUDGET_BUDGET;
    private static String VDD_PROJET_BUDGET_REALISE_ACTUALS;
    private static String VDD_PROJET_BUDGET_ENGAGE_DISTR;
    private static String VDD_PROJET_BUDGET_PREVISIONNELS;
    private static String VDD_PROJET_BUDGET_WORKORDER;
    private static String VDD_PROJET_BUDGET_ID;

    private static String VDD_PROJET_BUDGET_HEADER;
    private static String VDD_PROJET_BUDGET_HEADER_TYPE;
    private static String VDD_PROJET_BUDGET_HEADER_CODE;
    private static String VDD_PROJET_BUDGET_HEADER_IDBUDGET;
    private static String VDD_PROJET_BUDGET_HEADER_ENGAGE_COMMANDE;
    private static String VDD_PROJET_BUDGET_HEADER_DESCRIPTION;
    private static String VDD_PROJET_BUDGET_HEADER_BUDGET;
    private static String VDD_PROJET_BUDGET_HEADER_REALISE_ACTUALS;
    private static String VDD_PROJET_BUDGET_HEADER_PREVISIONNELS;

    private static String VDD_PROJET_BUDGET_LINE;
    private static String VDD_PROJET_BUDGET_LINE_PROJECT_NAME;
    private static String VDD_PROJET_BUDGET_LINE_VERSION;
    private static String VDD_PROJET_BUDGET_LINE_COST_VALUE;
    private static String VDD_PROJET_BUDGET_LINE_FIELD;
    private static String VDD_PROJET_BUDGET_LINE_MONTH;
    private static String VDD_PROJET_BUDGET_LINE_YEAR;
    private static String VDD_PROJET_BUDGET_LINE_IDBUDGET;

    private static String VDD_PROJET_PREVI;
    private static String VDD_PROJET_PREVI_TYPE;
    private static String VDD_PROJET_PREVI_CODE;
    private static String VDD_PROJET_PREVI_PHASE;
    private static String VDD_PROJET_PREVI_NATURE;
    private static String VDD_PROJET_PREVI_FINPHASE;
    private static String VDD_PROJET_PREVI_DESIGNATION;
    private static String VDD_PROJET_PREVI_DEBUTPHASE;
    private static String VDD_PROJET_PREVI_PREVISIONNEL;
    private static String VDD_PROJET_PREVI_PREVISIONNEL_TOTAL;
    private static String VDD_PROJET_PREVI_ID;

    private static String VDD_PROJET_PREVI_HEADER;
    private static String VDD_PROJET_PREVI_HEADER_TYPE;
    private static String VDD_PROJET_PREVI_HEADER_CODE;
    private static String VDD_PROJET_PREVI_HEADER_IDPREVI;
    private static String VDD_PROJET_PREVI_HEADER_PHASE;
    private static String VDD_PROJET_PREVI_HEADER_NATURE;
    private static String VDD_PROJET_PREVI_HEADER_FINPHASE;
    private static String VDD_PROJET_PREVI_HEADER_DESIGNATION;
    private static String VDD_PROJET_PREVI_HEADER_DEBUTPHASE;

    private static String VDD_PROJET_PREVI_LINE;
    private static String VDD_PROJET_PREVI_LINE_PROJECT_NAME;
    private static String VDD_PROJET_PREVI_LINE_VERSION;
    private static String VDD_PROJET_PREVI_LINE_COST_VALUE;
    private static String VDD_PROJET_PREVI_LINE_FIELD;
    private static String VDD_PROJET_PREVI_LINE_MONTH;
    private static String VDD_PROJET_PREVI_LINE_YEAR;
    private static String VDD_PROJET_PREVI_LINE_IDPREVI;

    private static String VDD_PROJET_FDL;
    private static String VDD_PROJET_FDL_MONTANT;
    private static String VDD_PROJET_FDL_N;
    private static String VDD_PROJET_FDL_PRESENTE_LE;
    private static String VDD_PROJET_FDL_TITRE;
    private static String VDD_PROJET_FDL_VALIDE_LE;

    private static String VDD_ET_QV_FDL;
    private static String VDD_ET_QV_FDL_MONTANT;
    private static String VDD_ET_QV_FDL_N;
    private static String VDD_ET_QV_FDL_PRESENTE_LE;
    private static String VDD_ET_QV_FDL_TITRE;
    private static String VDD_ET_QV_FDL_VALIDE_LE;
    private static String VDD_ET_QV_FDL_MTT_FDL;
    private static String VDD_ET_QV_FDL_MTT_BUDGET;
    private static String VDD_ET_QV_FDL_MTT_PREVI;
    private static String PROJECT_MTT_FDL;
    private static String PROJECT_MTT_BUDGET;
    private static String PROJECT_MTT_PREVI;
    private static String VDD_ET_QV_FDL_PROJECTNAME;

    private static String VDD_RESOURCE_HEADER;
    private static String VDD_RESOURCE_HEADER_NUM;
    private static String VDD_RESOURCE_HEADER_ORGA;
    private static String VDD_RESOURCE_HEADER_SKILL;
    private static String VDD_RESOURCE_HEADER_JOB;
    private static String VDD_RESOURCE_HEADER_MAIL1;
    private static String VDD_RESOURCE_HEADER_MAIL2;
    private static String VDD_RESOURCE_HEADER_MAIL3;
    private static String VDD_RESOURCE_HEADER_FIRSTNAME;
    private static String VDD_RESOURCE_HEADER_MIDDLENAME;
    private static String VDD_RESOURCE_HEADER_LASTNAME;
    private static String VDD_RESOURCE_HEADER_NAME;
    private static String VDD_RESOURCE_HEADER_IDRESOURCE;
    private static String VDD_RESOURCE_HEADER_ID;

    private static String VDD_RESOURCE_LINE;
    private static String VDD_RESOURCE_LINE_RESOURCE_NAME;
    private static String VDD_RESOURCE_LINE_VERSION;
    private static String VDD_RESOURCE_LINE_EFFORT_VALUE;
    private static String VDD_RESOURCE_LINE_STRING_VALUE;
    private static String VDD_RESOURCE_LINE_FIELD;
    private static String VDD_RESOURCE_LINE_MONTH;
    private static String VDD_RESOURCE_LINE_YEAR;
    private static String VDD_RESOURCE_LINE_IDRESOURCE;

    private static String VDD_LABORASS_LINE;
    private static String VDD_LABORASS_LINE_DURATION_VALUE;
    private static String VDD_LABORASS_LINE_EFFORT_VALUE;
    private static String VDD_LABORASS_LINE_COST_VALUE;
    private static String VDD_LABORASS_LINE_FIELD;
    private static String VDD_LABORASS_LINE_MONTH;
    private static String VDD_LABORASS_LINE_YEAR;
    private static String VDD_LABORASS_LINE_PROJECTIID;
    private static String VDD_LABORASS_LINE_RECORDID;
    private static String VDD_LABORASS_LINE_TASKIID;
    private static String VDD_LABORASS_LINE_VERSIONIID;

    private static String VDD_PROJET_QV_LINE;
    private static String VDD_PROJET_QV_LINE_PROJECT_NAME;
    private static String VDD_PROJET_QV_LINE_VERSION;
    private static String VDD_PROJET_QV_LINE_COST_VALUE;
    private static String VDD_PROJET_QV_LINE_DECIMAL_VALUE;
    private static String VDD_PROJET_QV_LINE_FIELD;
    private static String VDD_PROJET_QV_LINE_MONTH;
    private static String VDD_PROJET_QV_LINE_YEAR;
    private static String VDD_PROJET_QV_LINE_IDQV;

    private static String VDD_RES_DISTR;
    private static String VDD_RES_DISTR_FIELD1;
    private static String VDD_RES_DISTR_FIELD2;
    private static String VDD_RES_DISTR_FIELD3;
    private static String VDD_RES_DISTR_FIELD4;
    private static String VDD_RES_DISTR_FIELD5;
    private static String VDD_RES_DISTR_FIELD6;
    private static String VDD_RES_DISTR_FIELD7;
    private static String VDD_RES_DISTR_FIELD8;
    private static String VDD_RES_DISTR_FIELD9;
    private static String VDD_RES_DISTR_FIELD10;
    private static String VDD_RES_DISTR_FIELD11;
    private static String VDD_RES_DISTR_FIELD12;
    private static String VDD_RES_DISTR_FIELD13;
    private static String VDD_RES_DISTR_FIELD14;

    private static String VDD_ET_QV_TIMESHEET;

    private static String PROJECT_CHALLENGE;
    private static String PROJECT_ET_CHALLENGE_PREVI;

    private static final String UPDATE_PROJECT = "update_project";
    private static final String UPDATE_RESOURCE = "update_resource";
    private static final String UPDATE_TIMESHEET = "update_timesheet";
    private static final String UPDATE_FULL = "update_full";

    //private static int VDD_BACK_MONTH = -1;
    private static int NUMBER_OF_DAYS_BEFORE;
    private static int NUMBER_OF_MONTH_BEFORE;
    private static int NUMBER_OF_YEARS_BEFORE;

    private static int MIN_YEAR;
    private static int MAX_YEAR;

    private static final String VERSION = "1.12";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            initialisation();
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "[main][" + VERSION + "] Demarrage de l'API: " + new Date());
            connection();
            chargementParametreSciforma();
            if (args.length > 0) {
                if (args.length > 1) {
                    NUMBER_OF_MONTH_BEFORE = Integer.parseInt(args[1]);
                } else {
                    NUMBER_OF_MONTH_BEFORE = 0;
                }
                process(args[0]);
            }
            mSession.logout();
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "[main] Fermeture de l'API: " + new Date());
            System.exit(0);
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "main", ex);
            System.exit(1);
        }
    }

    private static void initialisation() {
        ctx = new FileSystemXmlApplicationContext(System.getProperty("user.dir") + System.getProperty("file.separator") + "config" + System.getProperty("file.separator") + "applicationContext.xml");
    }

    private static void connection() {
        Logger.getLogger(Run.class.getName()).log(Level.INFO, "[connexion] Chargement parametre connexion:" + new Date());
        Connector c = (Connector) ctx.getBean("sciforma");
        USER = c.getUSER();
        PWD = c.getPWD();
        IP = c.getIP();
        PORT = c.getPORT();
        CONTEXTE = c.getCONTEXTE();

        try {
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "[connexion] Initialisation de la Session:" + new Date());
            String url = IP + "/" + CONTEXTE;
            mSession = new Session(url);
            mSession.login(USER, PWD.toCharArray());
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "[connexion] Connecté: " + new Date() + " Ă  l'instance " + CONTEXTE);
        } catch (PSException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.ERROR, "[connexion] Erreur dans la connection de ... " + CONTEXTE, ex);
            System.exit(1);
        }
    }

    private static void chargementParametreSciforma() throws PSException {
        try {
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "[chargementParametreSciforma] Demarrage du chargement des parametres de l'application:" + new Date());
            projectManager = new ProjectManagerImpl(mSession);

            VDD_PROJET_PREVI = ((SciformaField) ctx.getBean("vdd_projet_previ")).getSciformaField();
            VDD_PROJET_PREVI_TYPE = ((SciformaField) ctx.getBean("vdd_projet_previ_type")).getSciformaField();
            VDD_PROJET_PREVI_CODE = ((SciformaField) ctx.getBean("vdd_projet_previ_code")).getSciformaField();
            VDD_PROJET_PREVI_PHASE = ((SciformaField) ctx.getBean("vdd_projet_previ_phase")).getSciformaField();
            VDD_PROJET_PREVI_NATURE = ((SciformaField) ctx.getBean("vdd_projet_previ_nature")).getSciformaField();
            VDD_PROJET_PREVI_FINPHASE = ((SciformaField) ctx.getBean("vdd_projet_previ_fin_de_la_phase")).getSciformaField();
            VDD_PROJET_PREVI_DESIGNATION = ((SciformaField) ctx.getBean("vdd_projet_previ_desi")).getSciformaField();
            VDD_PROJET_PREVI_DEBUTPHASE = ((SciformaField) ctx.getBean("vdd_projet_previ_debut")).getSciformaField();
            VDD_PROJET_PREVI_PREVISIONNEL = ((SciformaField) ctx.getBean("vdd_projet_previ_previ")).getSciformaField();
            VDD_PROJET_PREVI_PREVISIONNEL_TOTAL = ((SciformaField) ctx.getBean("vdd_projet_previ_previ_total")).getSciformaField();
            VDD_PROJET_PREVI_ID = ((SciformaField) ctx.getBean("vdd_projet_previ_id_previ")).getSciformaField();

            VDD_PROJET_PREVI_HEADER = ((SciformaField) ctx.getBean("vdd_projet_previ_header")).getSciformaField();
            VDD_PROJET_PREVI_HEADER_TYPE = ((SciformaField) ctx.getBean("vdd_projet_previ_header_type")).getSciformaField();
            VDD_PROJET_PREVI_HEADER_CODE = ((SciformaField) ctx.getBean("vdd_projet_previ_header_code")).getSciformaField();
            VDD_PROJET_PREVI_HEADER_IDPREVI = ((SciformaField) ctx.getBean("vdd_projet_previ_header_id_previ")).getSciformaField();
            VDD_PROJET_PREVI_HEADER_PHASE = ((SciformaField) ctx.getBean("vdd_projet_previ_header_phase")).getSciformaField();
            VDD_PROJET_PREVI_HEADER_NATURE = ((SciformaField) ctx.getBean("vdd_projet_previ_header_nature")).getSciformaField();
            VDD_PROJET_PREVI_HEADER_FINPHASE = ((SciformaField) ctx.getBean("vdd_projet_previ_header_fin_de_la_phase")).getSciformaField();
            VDD_PROJET_PREVI_HEADER_DESIGNATION = ((SciformaField) ctx.getBean("vdd_projet_previ_header_desi")).getSciformaField();
            VDD_PROJET_PREVI_HEADER_DEBUTPHASE = ((SciformaField) ctx.getBean("vdd_projet_previ_header_debut")).getSciformaField();

            VDD_PROJET_PREVI_LINE = ((SciformaField) ctx.getBean("vdd_projet_previ_line")).getSciformaField();
            VDD_PROJET_PREVI_LINE_PROJECT_NAME = ((SciformaField) ctx.getBean("vdd_projet_previ_line_project_name")).getSciformaField();
            VDD_PROJET_PREVI_LINE_VERSION = ((SciformaField) ctx.getBean("vdd_projet_previ_line_version")).getSciformaField();
            VDD_PROJET_PREVI_LINE_COST_VALUE = ((SciformaField) ctx.getBean("vdd_projet_previ_line_cost_value")).getSciformaField();
            VDD_PROJET_PREVI_LINE_FIELD = ((SciformaField) ctx.getBean("vdd_projet_previ_line_field")).getSciformaField();
            VDD_PROJET_PREVI_LINE_MONTH = ((SciformaField) ctx.getBean("vdd_projet_previ_line_month")).getSciformaField();
            VDD_PROJET_PREVI_LINE_YEAR = ((SciformaField) ctx.getBean("vdd_projet_previ_line_year")).getSciformaField();
            VDD_PROJET_PREVI_LINE_IDPREVI = ((SciformaField) ctx.getBean("vdd_projet_previ_line_id_previ")).getSciformaField();

            VDD_PROJET_BUDGET = ((SciformaField) ctx.getBean("vdd_projet_budget")).getSciformaField();
            VDD_PROJET_BUDGET_TYPE = ((SciformaField) ctx.getBean("vdd_projet_budget_type")).getSciformaField();
            VDD_PROJET_BUDGET_CODE = ((SciformaField) ctx.getBean("vdd_projet_budget_code")).getSciformaField();
            VDD_PROJET_BUDGET_REALISE_ACTUAL = ((SciformaField) ctx.getBean("vdd_projet_budget_realise_actual")).getSciformaField();
            VDD_PROJET_BUDGET_ENGAGE_COMMANDE = ((SciformaField) ctx.getBean("vdd_projet_budget_engage_commande")).getSciformaField();
            VDD_PROJET_BUDGET_DESCRIPTION = ((SciformaField) ctx.getBean("vdd_projet_budget_description")).getSciformaField();
            VDD_PROJET_BUDGET_BUDGET = ((SciformaField) ctx.getBean("vdd_projet_budget_budget")).getSciformaField();
            VDD_PROJET_BUDGET_ENGAGE_DISTR = ((SciformaField) ctx.getBean("vdd_projet_budget_engage_distr")).getSciformaField();
            VDD_PROJET_BUDGET_PREVISIONNELS = ((SciformaField) ctx.getBean("vdd_projet_budget_prévisionnels")).getSciformaField();
            VDD_PROJET_BUDGET_REALISE_ACTUALS = ((SciformaField) ctx.getBean("vdd_projet_budget_realise_actuals")).getSciformaField();
            VDD_PROJET_BUDGET_WORKORDER = ((SciformaField) ctx.getBean("vdd_projet_budget_workorder")).getSciformaField();
            VDD_PROJET_BUDGET_ID = ((SciformaField) ctx.getBean("vdd_projet_budget_id")).getSciformaField();

            VDD_PROJET_BUDGET_HEADER = ((SciformaField) ctx.getBean("vdd_projet_budget_header")).getSciformaField();
            VDD_PROJET_BUDGET_HEADER_TYPE = ((SciformaField) ctx.getBean("vdd_projet_budget_header_type")).getSciformaField();
            VDD_PROJET_BUDGET_HEADER_CODE = ((SciformaField) ctx.getBean("vdd_projet_budget_header_code")).getSciformaField();
            VDD_PROJET_BUDGET_HEADER_IDBUDGET = ((SciformaField) ctx.getBean("vdd_projet_budget_header_id_budget")).getSciformaField();
            VDD_PROJET_BUDGET_HEADER_ENGAGE_COMMANDE = ((SciformaField) ctx.getBean("vdd_projet_budget_header_engage_commande")).getSciformaField();
            VDD_PROJET_BUDGET_HEADER_DESCRIPTION = ((SciformaField) ctx.getBean("vdd_projet_budget_header_description")).getSciformaField();
            VDD_PROJET_BUDGET_HEADER_BUDGET = ((SciformaField) ctx.getBean("vdd_projet_budget_header_budget")).getSciformaField();
            VDD_PROJET_BUDGET_HEADER_PREVISIONNELS = ((SciformaField) ctx.getBean("vdd_projet_budget_header_prévisionnels")).getSciformaField();
            VDD_PROJET_BUDGET_HEADER_REALISE_ACTUALS = ((SciformaField) ctx.getBean("vdd_projet_budget_header_realise_actuals")).getSciformaField();

            VDD_PROJET_BUDGET_LINE = ((SciformaField) ctx.getBean("vdd_projet_budget_line")).getSciformaField();
            VDD_PROJET_BUDGET_LINE_PROJECT_NAME = ((SciformaField) ctx.getBean("vdd_projet_budget_line_project_name")).getSciformaField();
            VDD_PROJET_BUDGET_LINE_VERSION = ((SciformaField) ctx.getBean("vdd_projet_budget_line_version")).getSciformaField();
            VDD_PROJET_BUDGET_LINE_COST_VALUE = ((SciformaField) ctx.getBean("vdd_projet_budget_line_cost_value")).getSciformaField();
            VDD_PROJET_BUDGET_LINE_FIELD = ((SciformaField) ctx.getBean("vdd_projet_budget_line_field")).getSciformaField();
            VDD_PROJET_BUDGET_LINE_MONTH = ((SciformaField) ctx.getBean("vdd_projet_budget_line_month")).getSciformaField();
            VDD_PROJET_BUDGET_LINE_YEAR = ((SciformaField) ctx.getBean("vdd_projet_budget_line_year")).getSciformaField();
            VDD_PROJET_BUDGET_LINE_IDBUDGET = ((SciformaField) ctx.getBean("vdd_projet_budget_line_id_budget")).getSciformaField();

            VDD_RESOURCE_HEADER = ((SciformaField) ctx.getBean("vdd_resource_header")).getSciformaField();
            VDD_RESOURCE_HEADER_NUM = ((SciformaField) ctx.getBean("vdd_resource_header_num")).getSciformaField();
            VDD_RESOURCE_HEADER_ORGA = ((SciformaField) ctx.getBean("vdd_resource_header_orga")).getSciformaField();
            VDD_RESOURCE_HEADER_SKILL = ((SciformaField) ctx.getBean("vdd_resource_header_skill")).getSciformaField();
            VDD_RESOURCE_HEADER_JOB = ((SciformaField) ctx.getBean("vdd_resource_header_job")).getSciformaField();
            VDD_RESOURCE_HEADER_MAIL1 = ((SciformaField) ctx.getBean("vdd_resource_header_email1")).getSciformaField();
            VDD_RESOURCE_HEADER_MAIL2 = ((SciformaField) ctx.getBean("vdd_resource_header_email2")).getSciformaField();
            VDD_RESOURCE_HEADER_MAIL3 = ((SciformaField) ctx.getBean("vdd_resource_header_email3")).getSciformaField();
            VDD_RESOURCE_HEADER_FIRSTNAME = ((SciformaField) ctx.getBean("vdd_resource_header_firstname")).getSciformaField();
            VDD_RESOURCE_HEADER_MIDDLENAME = ((SciformaField) ctx.getBean("vdd_resource_header_middlename")).getSciformaField();
            VDD_RESOURCE_HEADER_LASTNAME = ((SciformaField) ctx.getBean("vdd_resource_header_lastname")).getSciformaField();
            VDD_RESOURCE_HEADER_NAME = ((SciformaField) ctx.getBean("vdd_resource_header_name")).getSciformaField();
            VDD_RESOURCE_HEADER_IDRESOURCE = ((SciformaField) ctx.getBean("vdd_resource_header_id_resource")).getSciformaField();
            VDD_RESOURCE_HEADER_ID = ((SciformaField) ctx.getBean("vdd_resource_header_id")).getSciformaField();

            VDD_RESOURCE_LINE = ((SciformaField) ctx.getBean("vdd_resource_line")).getSciformaField();
            VDD_RESOURCE_LINE_RESOURCE_NAME = ((SciformaField) ctx.getBean("vdd_resource_line_resource_name")).getSciformaField();
            VDD_RESOURCE_LINE_VERSION = ((SciformaField) ctx.getBean("vdd_resource_line_version")).getSciformaField();
            VDD_RESOURCE_LINE_EFFORT_VALUE = ((SciformaField) ctx.getBean("vdd_resource_effort_cost_value")).getSciformaField();
            VDD_RESOURCE_LINE_STRING_VALUE = ((SciformaField) ctx.getBean("vdd_resource_string_value")).getSciformaField();
            VDD_RESOURCE_LINE_FIELD = ((SciformaField) ctx.getBean("vdd_resource_line_field")).getSciformaField();
            VDD_RESOURCE_LINE_MONTH = ((SciformaField) ctx.getBean("vdd_resource_line_month")).getSciformaField();
            VDD_RESOURCE_LINE_YEAR = ((SciformaField) ctx.getBean("vdd_resource_line_year")).getSciformaField();
            VDD_RESOURCE_LINE_IDRESOURCE = ((SciformaField) ctx.getBean("vdd_resource_line_id_resource")).getSciformaField();

            VDD_LABORASS_LINE = ((SciformaField) ctx.getBean("vdd_labor_ass_line")).getSciformaField();
            VDD_LABORASS_LINE_DURATION_VALUE = ((SciformaField) ctx.getBean("vdd_labor_ass_line_duration_value")).getSciformaField();
            VDD_LABORASS_LINE_EFFORT_VALUE = ((SciformaField) ctx.getBean("vdd_labor_ass_line_effort_value")).getSciformaField();
            VDD_LABORASS_LINE_COST_VALUE = ((SciformaField) ctx.getBean("vdd_labor_ass_line_cost_value")).getSciformaField();
            VDD_LABORASS_LINE_FIELD = ((SciformaField) ctx.getBean("vdd_labor_ass_line_field")).getSciformaField();
            VDD_LABORASS_LINE_MONTH = ((SciformaField) ctx.getBean("vdd_labor_ass_line_month")).getSciformaField();
            VDD_LABORASS_LINE_YEAR = ((SciformaField) ctx.getBean("vdd_labor_ass_line_year")).getSciformaField();
            VDD_LABORASS_LINE_PROJECTIID = ((SciformaField) ctx.getBean("vdd_labor_ass_line_project_iid")).getSciformaField();
            VDD_LABORASS_LINE_RECORDID = ((SciformaField) ctx.getBean("vdd_labor_ass_line_record_id")).getSciformaField();
            VDD_LABORASS_LINE_TASKIID = ((SciformaField) ctx.getBean("vdd_labor_ass_line_task_iid")).getSciformaField();
            VDD_LABORASS_LINE_VERSIONIID = ((SciformaField) ctx.getBean("vdd_labor_ass_line_version_iid")).getSciformaField();

            VDD_PROJET_QV_LINE = ((SciformaField) ctx.getBean("vdd_projet_qv_line")).getSciformaField();
            VDD_PROJET_QV_LINE_PROJECT_NAME = ((SciformaField) ctx.getBean("vdd_projet_qv_line_project_name")).getSciformaField();
            VDD_PROJET_QV_LINE_VERSION = ((SciformaField) ctx.getBean("vdd_projet_qv_line_version")).getSciformaField();
            VDD_PROJET_QV_LINE_COST_VALUE = ((SciformaField) ctx.getBean("vdd_projet_qv_line_cost_value")).getSciformaField();
            VDD_PROJET_QV_LINE_DECIMAL_VALUE = ((SciformaField) ctx.getBean("vdd_projet_qv_line_decimal_value")).getSciformaField();
            VDD_PROJET_QV_LINE_FIELD = ((SciformaField) ctx.getBean("vdd_projet_qv_line_field")).getSciformaField();
            VDD_PROJET_QV_LINE_MONTH = ((SciformaField) ctx.getBean("vdd_projet_qv_line_month")).getSciformaField();
            VDD_PROJET_QV_LINE_YEAR = ((SciformaField) ctx.getBean("vdd_projet_qv_line_year")).getSciformaField();
            VDD_PROJET_QV_LINE_IDQV = ((SciformaField) ctx.getBean("vdd_projet_qv_line_id_qv")).getSciformaField();

            PROJECT_CHALLENGE = ((SciformaField) ctx.getBean("project_challenge")).getSciformaField();
            PROJECT_ET_CHALLENGE_PREVI = ((SciformaField) ctx.getBean("project_et_challenge_previ")).getSciformaField();

            VDD_RES_DISTR = ((SciformaField) ctx.getBean("vdd_ressource_distr")).getSciformaField();
            VDD_RES_DISTR_FIELD1 = ((SciformaField) ctx.getBean("vdd_ressource_distr_allo")).getSciformaField();
            VDD_RES_DISTR_FIELD2 = ((SciformaField) ctx.getBean("vdd_ressource_distr_actual_effort")).getSciformaField();
            VDD_RES_DISTR_FIELD3 = ((SciformaField) ctx.getBean("vdd_ressource_distr_actual_effort_adj")).getSciformaField();
            VDD_RES_DISTR_FIELD4 = ((SciformaField) ctx.getBean("vdd_ressource_distr_av_eff")).getSciformaField();
            VDD_RES_DISTR_FIELD5 = ((SciformaField) ctx.getBean("vdd_ressource_distr_pend_av_eff")).getSciformaField();
            VDD_RES_DISTR_FIELD6 = ((SciformaField) ctx.getBean("vdd_ressource_distr_rem_eff")).getSciformaField();
            VDD_RES_DISTR_FIELD7 = ((SciformaField) ctx.getBean("vdd_ressource_distr_rpt_tot_eff")).getSciformaField();
            VDD_RES_DISTR_FIELD8 = ((SciformaField) ctx.getBean("vdd_ressource_distr_sub_com_eff")).getSciformaField();
            VDD_RES_DISTR_FIELD9 = ((SciformaField) ctx.getBean("vdd_ressource_distr_sub_com_rate")).getSciformaField();
            VDD_RES_DISTR_FIELD10 = ((SciformaField) ctx.getBean("vdd_ressource_distr_ts_ac_eff")).getSciformaField();
            VDD_RES_DISTR_FIELD11 = ((SciformaField) ctx.getBean("vdd_ressource_distr_ts_com")).getSciformaField();
            VDD_RES_DISTR_FIELD12 = ((SciformaField) ctx.getBean("vdd_ressource_distr_tot_com_eff")).getSciformaField();
            VDD_RES_DISTR_FIELD13 = ((SciformaField) ctx.getBean("vdd_ressource_distr_tot_com_rate")).getSciformaField();
            VDD_RES_DISTR_FIELD14 = ((SciformaField) ctx.getBean("vdd_ressource_distr_tot_eff")).getSciformaField();

            MIN_YEAR = Integer.parseInt(((SciformaField) ctx.getBean("date_year_min")).getSciformaField());
            MAX_YEAR = Integer.parseInt(((SciformaField) ctx.getBean("date_year_max")).getSciformaField());

            VDD_ET_QV_TIMESHEET = ((SciformaField) ctx.getBean("vdd_et_qv_timesheet")).getSciformaField();
            //NUMBER_OF_DAYS_BEFORE  = Integer.parseInt(((SciformaField) ctx.getBean("number_of_days_before")).getSciformaField());
            //NUMBER_OF_MONTH_BEFORE  = Integer.parseInt(((SciformaField) ctx.getBean("number_of_months_before")).getSciformaField());
            //NUMBER_OF_YEARS_BEFORE  = Integer.parseInt(((SciformaField) ctx.getBean("number_of_years_before")).getSciformaField());

            VDD_ET_QV_FDL = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL")).getSciformaField();
            VDD_ET_QV_FDL_MONTANT = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_MONTANT")).getSciformaField();
            VDD_ET_QV_FDL_N = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_N")).getSciformaField();
            VDD_ET_QV_FDL_PRESENTE_LE = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_PRESENTE_LE")).getSciformaField();
            VDD_ET_QV_FDL_TITRE = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_TITRE")).getSciformaField();
            VDD_ET_QV_FDL_VALIDE_LE = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_VALIDE_LE")).getSciformaField();
            VDD_ET_QV_FDL_MTT_FDL = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_MTT_FDL")).getSciformaField();
            VDD_ET_QV_FDL_MTT_BUDGET = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_MTT_BUDGET")).getSciformaField();
            VDD_ET_QV_FDL_MTT_PREVI = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_MTT_PREVI")).getSciformaField();
            PROJECT_MTT_FDL = ((SciformaField) ctx.getBean("PROJECT_MTT_FDL")).getSciformaField();
            PROJECT_MTT_BUDGET = ((SciformaField) ctx.getBean("PROJECT_MTT_BUDGET")).getSciformaField();
            PROJECT_MTT_PREVI = ((SciformaField) ctx.getBean("PROJECT_MTT_PREVI")).getSciformaField();
            VDD_ET_QV_FDL_PROJECTNAME = ((SciformaField) ctx.getBean("VDD_ET_QV_FDL_PROJECTNAME")).getSciformaField();

            VDD_PROJET_FDL = ((SciformaField) ctx.getBean("VDD_PROJET_FDL")).getSciformaField();
            VDD_PROJET_FDL_MONTANT = ((SciformaField) ctx.getBean("VDD_PROJET_FDL_MONTANT")).getSciformaField();
            VDD_PROJET_FDL_N = ((SciformaField) ctx.getBean("VDD_PROJET_FDL_N")).getSciformaField();
            VDD_PROJET_FDL_PRESENTE_LE = ((SciformaField) ctx.getBean("VDD_PROJET_FDL_PRESENTE_LE")).getSciformaField();
            VDD_PROJET_FDL_TITRE = ((SciformaField) ctx.getBean("VDD_PROJET_FDL_TITRE")).getSciformaField();
            VDD_PROJET_FDL_VALIDE_LE = ((SciformaField) ctx.getBean("VDD_PROJET_FDL_VALIDE_LE")).getSciformaField();

            projectList = mSession.getProjectList(Project.VERSION_WORKING, Project.READWRITE_ACCESS);
            listResource = mSession.getWorkingResourceList();
            //On rajoute également la liste des projets objectifs
            List<Project> lpObjective = mSession.getProjectList(Project.VERSION_OBJECTIVE, Project.READWRITE_ACCESS);
            Iterator lpito = lpObjective.iterator();
            while (lpito.hasNext()) {
                Project p = (Project) lpito.next();
                projectList.add(p);
            }
            List<Project> lpWork2 = mSession.getProjectList(Project.VERSION_WORKING2, Project.READWRITE_ACCESS);
            Iterator lpitw2 = lpWork2.iterator();
            while (lpitw2.hasNext()) {
                Project p = (Project) lpitw2.next();
                projectList.add(p);
            }
            List<Project> lpWork3 = mSession.getProjectList(Project.VERSION_WORKING3, Project.READWRITE_ACCESS);
            Iterator lpitw3 = lpWork3.iterator();
            while (lpitw3.hasNext()) {
                Project p = (Project) lpitw3.next();
                projectList.add(p);
            }
            List<Project> lpWork4 = mSession.getProjectList(Project.VERSION_WORKING4, Project.READWRITE_ACCESS);
            Iterator lpitw4 = lpWork4.iterator();
            while (lpitw4.hasNext()) {
                Project p = (Project) lpitw4.next();
                projectList.add(p);
            }
            List<Project> lpWork5 = mSession.getProjectList(Project.VERSION_WORKING5, Project.READWRITE_ACCESS);
            Iterator lpitw5 = lpWork5.iterator();
            while (lpitw5.hasNext()) {
                Project p = (Project) lpitw5.next();
                projectList.add(p);
            }

            Logger.getLogger(Run.class.getName()).log(Level.INFO, "[chargementParametreSciforma] Fin du chargement des parametres de l'application:" + new Date());
        } catch (Exception ex) {
            Logger.getLogger(Run.class.getName()).log(Level.ERROR, "[chargementParametreSciforma] Erreur dans la lecture l'intitialisation du parametrage " + new Date(), ex);
            mSession.logout();
            System.exit(1);
        }
    }

    private static void updateDVRProjectBudget(Project p) throws PSException {
        try {
            //if(p.getStringField("Name").equals("Expertise rack électronique de freinage Breda, Pax et locomotives")){
            p.open(false);
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "updateDVRProjectBudget > project = " + p.getStringField("Name") + " <" + p.getDateField("Start") + "> à <" + p.getDateField("Finish") + ">");
            List vpb = mSession.getDataViewRowList(VDD_PROJET_BUDGET, p);
            Iterator vpbit = vpb.iterator();

            List vpbh = mSession.getDataViewRowList(VDD_PROJET_BUDGET_HEADER, p);
            Iterator vpbhit = vpbh.iterator();
            while (vpbhit.hasNext()) {
                DataViewRow dvr_vpbh = (DataViewRow) vpbhit.next();
                dvr_vpbh.remove();
            }

            List vpbl = mSession.getDataViewRowList(VDD_PROJET_BUDGET_LINE, p);
            Iterator vpblit = vpbl.iterator();
            while (vpblit.hasNext()) {
                DataViewRow dvr_vpbl = (DataViewRow) vpblit.next();
                dvr_vpbl.remove();
            }
            while (vpbit.hasNext()) {
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "Données pour la VDD " + VDD_PROJET_BUDGET + " trouvé !");
                DataViewRow dvr_vpb = (DataViewRow) vpbit.next();

                DataViewRow dvr_vpbh = new DataViewRow(VDD_PROJET_BUDGET_HEADER, p, DataViewRow.CREATE);

                dvr_vpbh.setDoubleField(VDD_PROJET_BUDGET_HEADER_BUDGET, dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_BUDGET));
                dvr_vpbh.setStringField(VDD_PROJET_BUDGET_HEADER_CODE, dvr_vpb.getStringField(VDD_PROJET_BUDGET_CODE));
                dvr_vpbh.setStringField(VDD_PROJET_BUDGET_HEADER_DESCRIPTION, dvr_vpb.getStringField(VDD_PROJET_BUDGET_DESCRIPTION));
                dvr_vpbh.setDoubleField(VDD_PROJET_BUDGET_HEADER_ENGAGE_COMMANDE, dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_ENGAGE_COMMANDE));
                dvr_vpbh.setDoubleField(VDD_PROJET_BUDGET_HEADER_PREVISIONNELS, dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_PREVISIONNELS));
                dvr_vpbh.setDoubleField(VDD_PROJET_BUDGET_HEADER_IDBUDGET, dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_ID));
                dvr_vpbh.setDoubleField(VDD_PROJET_BUDGET_HEADER_REALISE_ACTUALS, dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_REALISE_ACTUALS));
                dvr_vpbh.setStringField(VDD_PROJET_BUDGET_HEADER_TYPE, dvr_vpb.getStringField(VDD_PROJET_BUDGET_TYPE));
                try {
                    List<DoubleDatedData> ldddw = dvr_vpb.getDatedData(VDD_PROJET_BUDGET_WORKORDER, DatedData.MONTH);
                    saveDVRBudgetLine(dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_ID), VDD_PROJET_BUDGET_WORKORDER, VDD_PROJET_BUDGET_LINE, p, ldddw, VDD_PROJET_BUDGET_LINE_COST_VALUE);
                } catch (PSException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectBudget - 1", ex);
                }
                try {
                    List<DoubleDatedData> lddd = dvr_vpb.getDatedData(VDD_PROJET_BUDGET_REALISE_ACTUAL, DatedData.MONTH);
                    saveDVRBudgetLine(dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_ID), VDD_PROJET_BUDGET_REALISE_ACTUAL, VDD_PROJET_BUDGET_LINE, p, lddd, VDD_PROJET_BUDGET_LINE_COST_VALUE);
                } catch (PSException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectBudget - 2", ex);
                }
                try {
                    List<DoubleDatedData> ldddra = dvr_vpb.getDatedData("Réalisé (Actuals)", DatedData.MONTH);
                    saveDVRBudgetLine(dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_ID), "Réalisé (Actuals)", VDD_PROJET_BUDGET_LINE, p, ldddra, VDD_PROJET_BUDGET_LINE_COST_VALUE);
                } catch (PSException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectBudget - 3", ex);
                }
                try {
                    List<DoubleDatedData> ldddp = dvr_vpb.getDatedData(VDD_PROJET_BUDGET_HEADER_PREVISIONNELS, DatedData.MONTH);
                    saveDVRBudgetLine(dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_ID), VDD_PROJET_BUDGET_HEADER_PREVISIONNELS, VDD_PROJET_BUDGET_LINE, p, ldddp, VDD_PROJET_BUDGET_LINE_COST_VALUE);
                } catch (PSException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectBudget - 4", ex);
                }
                //List<DoubleDatedData> ldddr = dvr_vpb.getDatedData(VDD_PROJET_BUDGET_ENGAGE_DISTR, DatedData.MONTH);
                //saveDVRBudgetLine(dvr_vpb.getDoubleField(VDD_PROJET_BUDGET_ID), VDD_PROJET_BUDGET_ENGAGE_DISTR, VDD_PROJET_BUDGET_LINE, p, ldddr, VDD_PROJET_BUDGET_LINE_COST_VALUE);
            }
            p.save();
        } catch (InvalidDataException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectBudget", ex);
            System.exit(1);
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectBudget", ex);
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectBudget", ex);
            System.exit(1);
        }
    }

    private static void updateDVRProject(Project p) throws PSException {
        try {
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "updateDVRProject > project = " + p.getStringField("Name") + " <" + p.getDateField("Start") + "> à <" + p.getDateField("Finish") + ">");

            List vpbl = mSession.getDataViewRowList(VDD_PROJET_QV_LINE, p);
            Iterator vpblit = vpbl.iterator();
            while (vpblit.hasNext()) {
                DataViewRow dvr_vpbl = (DataViewRow) vpblit.next();
                dvr_vpbl.remove();
            }
            List<DoubleDatedData> ldddw = p.getDatedData(PROJECT_CHALLENGE, DatedData.MONTH, p.getDateField("Start"), p.getDateField("Finish"));
            saveDVRLine(p.getDoubleField("Internal ID"), PROJECT_CHALLENGE, VDD_PROJET_QV_LINE, p, ldddw, VDD_PROJET_QV_LINE_DECIMAL_VALUE, true);
            List<DoubleDatedData> lddd = new ArrayList<>();
            for (DoubleDatedData d : ldddw) {
                List vpb = mSession.getDataViewRowList(VDD_PROJET_BUDGET, p);
                Iterator vpbit = vpb.iterator();
                while (vpbit.hasNext()) {
                    DataViewRow dvr_vpb = (DataViewRow) vpbit.next();
                    String code = dvr_vpb.getStringField(VDD_PROJET_BUDGET_CODE);
                    List vp = mSession.getDataViewRowList(VDD_PROJET_PREVI, p);
                    Iterator vpit = vp.iterator();
                    while (vpit.hasNext()) {
                        DataViewRow dvr_vp = (DataViewRow) vpit.next();
                        if (code.equals(dvr_vp.getStringField(VDD_PROJET_PREVI_CODE))) {
                            List l = dvr_vp.getDatedData(VDD_PROJET_PREVI_PREVISIONNEL, DatedData.MONTH, p.getDateField("Start"), p.getDateField("Finish"));
                            Iterator it = l.iterator();
                            while (it.hasNext()) {
                                DoubleDatedData dd = (DoubleDatedData) it.next();
                                try {
                                    if (d.getStart().equals(dd.getStart())) {
                                        double value = 0;
                                        double a = d.getData();
                                        double b = dd.getData();
                                        value = a * b;
                                        DoubleDatedData ddd = new DoubleDatedData(value, d.getStart(), d.getFinish());
                                        Logger.getLogger(Run.class.getName()).log(Level.INFO, "doubleDatedData = " + ddd.getData() + " <" + ddd.getStart() + "> à <" + ddd.getFinish() + ">");
                                        lddd.add(ddd);
                                    }
                                } catch (NullPointerException ex) {
                                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "Code: " + code, ex);
                                }
                            }
                        }
                    }
                }
            }
            //List<DoubleDatedData> lddd = p.getDatedData(PROJECT_ET_CHALLENGE_PREVI, DatedData.MONTH, p.getDateField("Start"), p.getDateField("Finish"));
            saveDVRLine(p.getDoubleField("Internal ID"), PROJECT_ET_CHALLENGE_PREVI, VDD_PROJET_QV_LINE, p, lddd, VDD_PROJET_QV_LINE_COST_VALUE, true);

            p.save();
        } catch (InvalidDataException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProject", ex);
            System.exit(1);
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProject", ex);
        } catch (NullPointerException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProject", ex);
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProject", ex);
            //System.exit(1);
        }
    }

    private static void updateDVRProjectPrevisionnel(Project p) throws PSException {
        try {
            //if(p.getStringField("Name").equals("Expertise rack électronique de freinage Breda, Pax et locomotives")){
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "updateDVRProjectPrevisionnel > project = " + p.getStringField("Name") + " <" + p.getDateField("Start") + "> à <" + p.getDateField("Finish") + ">");
            List vpb = mSession.getDataViewRowList(VDD_PROJET_PREVI, p);
            Iterator vpbit = vpb.iterator();

            List vpbh = mSession.getDataViewRowList(VDD_PROJET_PREVI_HEADER, p);
            Iterator vpbhit = vpbh.iterator();
            while (vpbhit.hasNext()) {
                DataViewRow dvr_vpbh = (DataViewRow) vpbhit.next();
                dvr_vpbh.remove();
            }

            List vpbl = mSession.getDataViewRowList(VDD_PROJET_PREVI_LINE, p);
            Iterator vpblit = vpbl.iterator();
            while (vpblit.hasNext()) {
                DataViewRow dvr_vpbl = (DataViewRow) vpblit.next();
                dvr_vpbl.remove();
            }
            p.save();
            while (vpbit.hasNext()) {
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "Données pour la VDD " + VDD_PROJET_PREVI + " trouvé !");
                try {
                    DataViewRow dvr_vpb = (DataViewRow) vpbit.next();

                    DataViewRow dvr_vpbh = new DataViewRow(VDD_PROJET_PREVI_HEADER, p, DataViewRow.CREATE);

                    dvr_vpbh.setStringField(VDD_PROJET_PREVI_HEADER_CODE, dvr_vpb.getStringField(VDD_PROJET_PREVI_CODE));
                    dvr_vpbh.setDateField(VDD_PROJET_PREVI_HEADER_DEBUTPHASE, dvr_vpb.getDateField(VDD_PROJET_PREVI_DEBUTPHASE));
                    dvr_vpbh.setStringField(VDD_PROJET_PREVI_HEADER_DESIGNATION, dvr_vpb.getStringField(VDD_PROJET_PREVI_DESIGNATION));
                    dvr_vpbh.setDateField(VDD_PROJET_PREVI_HEADER_FINPHASE, dvr_vpb.getDateField(VDD_PROJET_PREVI_FINPHASE));
                    dvr_vpbh.setStringField(VDD_PROJET_PREVI_HEADER_NATURE, dvr_vpb.getStringField(VDD_PROJET_PREVI_NATURE));
                    dvr_vpbh.setDoubleField(VDD_PROJET_PREVI_HEADER_IDPREVI, dvr_vpb.getDoubleField(VDD_PROJET_PREVI_ID));
                    dvr_vpbh.setStringField(VDD_PROJET_PREVI_HEADER_PHASE, dvr_vpb.getStringField(VDD_PROJET_PREVI_PHASE));
                    dvr_vpbh.setStringField(VDD_PROJET_PREVI_HEADER_TYPE, dvr_vpb.getStringField(VDD_PROJET_PREVI_TYPE));

                    List<DoubleDatedData> ldddw = dvr_vpb.getDatedData(VDD_PROJET_PREVI_PREVISIONNEL, DatedData.MONTH);
                    saveDVRProjectPrevisionnel(dvr_vpb.getDoubleField(VDD_PROJET_PREVI_ID), VDD_PROJET_PREVI_PREVISIONNEL, VDD_PROJET_PREVI_LINE, p, ldddw, VDD_PROJET_PREVI_LINE_COST_VALUE);

                    List<DoubleDatedData> lddd = dvr_vpb.getDatedData(VDD_PROJET_PREVI_PREVISIONNEL_TOTAL, DatedData.MONTH);
                    saveDVRProjectPrevisionnel(dvr_vpb.getDoubleField(VDD_PROJET_PREVI_ID), VDD_PROJET_PREVI_PREVISIONNEL_TOTAL, VDD_PROJET_PREVI_LINE, p, lddd, VDD_PROJET_PREVI_LINE_COST_VALUE);
                } catch (PSException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectPrevisionnel - while", ex);
                } catch (NullPointerException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectPrevisionnel - while", ex);
                }
            }
            p.save();
        } catch (InvalidDataException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectPrevisionnel", ex);
            System.exit(1);
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectPrevisionnel", ex);
        } catch (NullPointerException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectPrevisionnel", ex);
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectPrevisionnel", ex);
            System.exit(1);
        }
    }

    private static void updateDVRProjectFicheDeLancement(Project p) throws PSException {
        try {
            //if(p.getStringField("Name").equals("Expertise rack électronique de freinage Breda, Pax et locomotives")){
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "updateDVRProjectFicheDeLancement > project = " + p.getStringField("Name"));
            List vpb = mSession.getDataViewRowList(VDD_ET_QV_FDL, p);
            Iterator vpbit = vpb.iterator();

            while (vpbit.hasNext()) {
                DataViewRow dvr_vpbh = (DataViewRow) vpbit.next();
                dvr_vpbh.remove();
            }

            List vpbl = mSession.getDataViewRowList(VDD_PROJET_FDL, p);
            Iterator vpblit = vpbl.iterator();
            while (vpblit.hasNext()) {
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "Données pour la VDD " + VDD_PROJET_FDL + " trouvé !");
                try {
                    DataViewRow dvr_vpb = (DataViewRow) vpblit.next();

                    DataViewRow dvr_vpbh = new DataViewRow(VDD_ET_QV_FDL, p, DataViewRow.CREATE);
                    dvr_vpbh.setDoubleField(VDD_ET_QV_FDL_MONTANT, dvr_vpb.getDoubleField(VDD_PROJET_FDL_MONTANT));
                    dvr_vpbh.setStringField(VDD_ET_QV_FDL_N, dvr_vpb.getStringField(VDD_PROJET_FDL_N));
                    dvr_vpbh.setDateField(VDD_ET_QV_FDL_PRESENTE_LE, dvr_vpb.getDateField(VDD_PROJET_FDL_PRESENTE_LE));
                    dvr_vpbh.setStringField(VDD_ET_QV_FDL_TITRE, dvr_vpb.getStringField(VDD_PROJET_FDL_TITRE));
                    dvr_vpbh.setDateField(VDD_ET_QV_FDL_VALIDE_LE, dvr_vpb.getDateField(VDD_PROJET_FDL_VALIDE_LE));
                    dvr_vpbh.setDoubleField(VDD_ET_QV_FDL_MTT_FDL, p.getDoubleField(PROJECT_MTT_FDL));
                    dvr_vpbh.setDoubleField(VDD_ET_QV_FDL_MTT_BUDGET, p.getDoubleField(PROJECT_MTT_BUDGET));
                    dvr_vpbh.setDoubleField(VDD_ET_QV_FDL_MTT_PREVI, p.getDoubleField(PROJECT_MTT_PREVI));
                    dvr_vpbh.setStringField(VDD_ET_QV_FDL_PROJECTNAME, p.getStringField("Name"));
                } catch (PSException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectFicheDeLancement - while", ex);
                } catch (NullPointerException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectFicheDeLancement - while", ex);
                }
            }
            p.save();
        } catch (InvalidDataException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectFicheDeLancement", ex);
            System.exit(1);
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectFicheDeLancement", ex);
        } catch (NullPointerException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectFicheDeLancement", ex);
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRProjectFicheDeLancement", ex);
            System.exit(1);
        }
    }

    private static void updateDVRLaborAssignement(Project p) throws PSException {
        try {
            //if(p.getStringField("Name").equals("Expertise rack électronique de freinage Breda, Pax et locomotives")){
            Logger.getLogger(Run.class.getName()).log(Level.INFO, "updateDVRLaborAssignement > project = " + p.getStringField("Name") + " <" + p.getDateField("Start") + "> à <" + p.getDateField("Finish") + ">");
            TaskOutlineList tasks = p.getTaskOutlineList();
            Iterator taskIt = tasks.iterator();
            while (taskIt.hasNext()) {
                Task t = (Task) taskIt.next();
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "task = " + t.getStringField("Name") + " <" + t.getDateField("Start") + "> à <" + t.getDateField("Finish") + ">");
                List<ResAssignment> resAssignList = t.getResAssignmentList();
                Iterator resIt = resAssignList.iterator();
                while (resIt.hasNext()) {
                    try {
                        ResAssignment res = (ResAssignment) resIt.next();
                        List lvddrow = mSession.getDataViewRowList(VDD_LABORASS_LINE, res);
                        Iterator lvddrowIt = lvddrow.iterator();
                        while (lvddrowIt.hasNext()) {
                            DataViewRow dvr = (DataViewRow) lvddrowIt.next();
                            dvr.remove();
                        }
                        saveDVRLaborAss(res, "Total Effort", VDD_LABORASS_LINE, p, t, VDD_LABORASS_LINE_EFFORT_VALUE);
                        saveDVRLaborAss(res, "Actual Effort", VDD_LABORASS_LINE, p, t, VDD_LABORASS_LINE_EFFORT_VALUE);
                        saveDVRLaborAss(res, "Baseline1 Effort", VDD_LABORASS_LINE, p, t, VDD_LABORASS_LINE_EFFORT_VALUE);
                        saveDVRLaborAss(res, "Remaining Effort", VDD_LABORASS_LINE, p, t, VDD_LABORASS_LINE_EFFORT_VALUE);
                        /*saveDVRLaborAss(res, "Total Cost" , VDD_LABORASS_LINE, p, t, VDD_LABORASS_LINE_COST_VALUE);
                         saveDVRLaborAss(res, "Actual Cost" , VDD_LABORASS_LINE, p, t, VDD_LABORASS_LINE_COST_VALUE);
                         saveDVRLaborAss(res, "Baseline1 Cost" , VDD_LABORASS_LINE, p, t, VDD_LABORASS_LINE_COST_VALUE);
                         saveDVRLaborAss(res, "Remaining Cost" , VDD_LABORASS_LINE, p, t, VDD_LABORASS_LINE_COST_VALUE);*/
                    } catch (PSException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement - while", ex);
                    } catch (NullPointerException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement - while", ex);
                    }
                }
            }
            p.save();
        } catch (InvalidDataException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement", ex);
            System.exit(1);
        } catch (LockException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement", ex);
        } catch (NullPointerException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement", ex);
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement", ex);
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement", ex);
            System.exit(1);
        }
    }

    private static void saveDVRLaborAss(ResAssignment res, String field_name, String vdd_name, Project p, Task t, String field_toSave) throws PSException {
        Global g = new Global();
        List<DoubleDatedData> lddd = res.getDatedData(field_name, DatedData.MONTH);
        Iterator ldddIt = lddd.iterator();
        while (ldddIt.hasNext()) {
            try {
                DoubleDatedData ddd = (DoubleDatedData) ldddIt.next();
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "doubleDatedData = " + ddd.getData() + " <" + ddd.getStart() + "> à <" + ddd.getFinish() + ">");
                Calendar cal = Calendar.getInstance();
                cal.setTime(ddd.getStart());
                DataViewRow new_dvr = new DataViewRow(vdd_name, res, DataViewRow.CREATE);
                new_dvr.setStringField(VDD_LABORASS_LINE_FIELD, field_name);
                new_dvr.setDoubleField(field_toSave, ddd.getData());
                new_dvr.setStringField(VDD_LABORASS_LINE_PROJECTIID, p.getStringField("Name"));
                new_dvr.setStringField(VDD_LABORASS_LINE_TASKIID, t.getStringField("Name"));
                new_dvr.setStringField(VDD_LABORASS_LINE_VERSIONIID, p.getStringField("Version"));
                new_dvr.setStringField(VDD_LABORASS_LINE_RECORDID, res.getStringField("Name"));
                new_dvr.setIntField(VDD_LABORASS_LINE_MONTH, cal.get(Calendar.MONTH));
                new_dvr.setIntField(VDD_LABORASS_LINE_YEAR, cal.get(Calendar.YEAR));
            } catch (PSException ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement", ex);
            } catch (NullPointerException ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRLaborAssignement", ex);
            }
        }
    }

    private static void saveDVRBudgetLine(Double id, String field_name, String vdd_name, Project p, List<DoubleDatedData> lddd, String field_toSave) throws PSException {
        Iterator ldddIt = lddd.iterator();
        while (ldddIt.hasNext()) {
            try {
                DoubleDatedData ddd = (DoubleDatedData) ldddIt.next();
                CostData cd = (CostData) ddd.getValue();
                if (cd != null) {
                    Logger.getLogger(Run.class.getName()).log(Level.INFO, "doubleDatedData = " + ddd.getData() + " <" + ddd.getStart() + "> à <" + ddd.getFinish() + ">");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(ddd.getStart());
                    DataViewRow new_dvr = new DataViewRow(vdd_name, p, DataViewRow.CREATE);
                    new_dvr.setStringField(VDD_PROJET_BUDGET_LINE_FIELD, field_name);
                    new_dvr.setDoubleField(field_toSave, ddd.getData());
                    new_dvr.setStringField(VDD_PROJET_BUDGET_LINE_PROJECT_NAME, p.getStringField("Name"));
                    new_dvr.setDoubleField(VDD_PROJET_BUDGET_LINE_IDBUDGET, id);
                    new_dvr.setStringField(VDD_PROJET_BUDGET_LINE_VERSION, p.getStringField("Version"));
                    new_dvr.setIntField(VDD_PROJET_BUDGET_LINE_MONTH, cal.get(Calendar.MONTH));
                    new_dvr.setIntField(VDD_PROJET_BUDGET_LINE_YEAR, cal.get(Calendar.YEAR));
                }
            } catch (PSException ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRBudgetLine", ex);
            } catch (NullPointerException ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRBudgetLine", ex);
            } catch (Exception ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRBudgetLine", ex);
                System.exit(1);
            }
        }
    }

    private static void saveDVRLine(Double id, String field_name, String vdd_name, Project p, List<DoubleDatedData> lddd, String field_toSave, boolean costdata) throws PSException {
        Iterator ldddIt = lddd.iterator();
        while (ldddIt.hasNext()) {
            try {
                DoubleDatedData ddd = (DoubleDatedData) ldddIt.next();
                CostData cd = null;
                Double d = null;
                if (!costdata) {
                    cd = (CostData) ddd.getValue();
                } else {
                    d = (Double) ddd.getValue();
                }
                if (cd != null || d != null) {
                    Logger.getLogger(Run.class.getName()).log(Level.INFO, "doubleDatedData = " + ddd.getData() + " <" + ddd.getStart() + "> à <" + ddd.getFinish() + ">");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(ddd.getStart());
                    DataViewRow new_dvr = new DataViewRow(vdd_name, p, DataViewRow.CREATE);
                    new_dvr.setStringField(VDD_PROJET_QV_LINE_FIELD, field_name);
                    new_dvr.setDoubleField(field_toSave, ddd.getData());
                    new_dvr.setStringField(VDD_PROJET_QV_LINE_PROJECT_NAME, p.getStringField("Name"));
                    new_dvr.setDoubleField(VDD_PROJET_QV_LINE_IDQV, id);
                    new_dvr.setStringField(VDD_PROJET_QV_LINE_VERSION, p.getStringField("Version"));
                    new_dvr.setIntField(VDD_PROJET_QV_LINE_MONTH, cal.get(Calendar.MONTH));
                    new_dvr.setIntField(VDD_PROJET_QV_LINE_YEAR, cal.get(Calendar.YEAR));
                }
            } catch (PSException ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRLine", ex);
            } catch (NullPointerException ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRLine", ex);
            } catch (Exception ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRLine", ex);
                System.exit(1);
            }
        }
    }

    private static void saveDVRProjectPrevisionnel(Double id, String field_name, String vdd_name, Project p, List<DoubleDatedData> lddd, String field_toSave) throws PSException {
        Iterator ldddIt = lddd.iterator();
        while (ldddIt.hasNext()) {
            try {
                DoubleDatedData ddd = (DoubleDatedData) ldddIt.next();
                CostData cd = (CostData) ddd.getValue();
                if (cd != null) {
                    Logger.getLogger(Run.class.getName()).log(Level.INFO, "doubleDatedData = " + ddd.getData() + " <" + ddd.getStart() + "> à <" + ddd.getFinish() + ">");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(ddd.getStart());
                    DataViewRow new_dvr = new DataViewRow(vdd_name, p, DataViewRow.CREATE);
                    new_dvr.setStringField(VDD_PROJET_PREVI_LINE_FIELD, field_name);
                    new_dvr.setDoubleField(field_toSave, ddd.getData());
                    new_dvr.setStringField(VDD_PROJET_PREVI_LINE_PROJECT_NAME, p.getStringField("Name"));
                    new_dvr.setDoubleField(VDD_PROJET_PREVI_LINE_IDPREVI, id);
                    new_dvr.setStringField(VDD_PROJET_PREVI_LINE_VERSION, p.getStringField("Version"));
                    new_dvr.setIntField(VDD_PROJET_PREVI_LINE_MONTH, cal.get(Calendar.MONTH));
                    new_dvr.setIntField(VDD_PROJET_PREVI_LINE_YEAR, cal.get(Calendar.YEAR));
                }
            } catch (NullPointerException ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRProjectPrevisionnel", ex);
            } catch (PSException ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRProjectPrevisionnel", ex);
            } catch (Exception ex) {
                org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRProjectPrevisionnel", ex);
                System.exit(1);
            }
        }
    }

    private static void process(String type) throws PSException {
        int nb = 1;
        if (type.equals(UPDATE_FULL) || type.equals(UPDATE_PROJECT)) {
            Iterator lpit = projectList.iterator();
            while (lpit.hasNext()) {
                try {
                    Project p = (Project) lpit.next();
                    //if (p.getStringField("Name").equals("AMC BREDA/COSTA: AMC TV et AMC WIFI")) {
                    p.open(false);
                    Logger.getLogger(Run.class.getName()).log(Level.INFO, "============================================================================");
                    Logger.getLogger(Run.class.getName()).log(Level.INFO, "[" + nb + "/" + projectList.size() + "] Traitement du projet " + p.getStringField("Name") + "[" + new Date() + "]");
                    Logger.getLogger(Run.class.getName()).log(Level.INFO, "============================================================================");
                    try {
                        updateDVRProjectBudget(p);
                    } catch (NullPointerException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectBudget", ex);
                    } catch (PSException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectBudget", ex);
                    } catch (Exception ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectBudget", ex);
                        p.close();
                        System.exit(1);
                    }
                    try {
                        updateDVRProjectPrevisionnel(p);
                    } catch (NullPointerException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectPrevisionnel", ex);
                    } catch (PSException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectPrevisionnel", ex);
                    } catch (Exception ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectPrevisionnel", ex);
                        p.close();
                        System.exit(1);
                    }
                    try {
                        updateDVRProjectFicheDeLancement(p);
                    } catch (NullPointerException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectPrevisionnel", ex);
                    } catch (PSException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectPrevisionnel", ex);
                    } catch (Exception ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProjectPrevisionnel", ex);
                        p.close();
                        System.exit(1);
                    }
                    try {
                        updateDVRLaborAssignement(p);
                    } catch (NullPointerException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRLaborAssignement", ex);
                    } catch (PSException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRLaborAssignement", ex);
                    } catch (Exception ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRLaborAssignement", ex);
                        p.close();
                        System.exit(1);
                    }
                    try {
                        updateDVRProject(p);
                    } catch (NullPointerException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProject", ex);
                    } catch (PSException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProject", ex);
                    } catch (Exception ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process - updateDVRProject", ex);
                        p.close();
                        System.exit(1);
                    }
                    p.save();
                    p.close();
                    //}
                } catch (NullPointerException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process", ex);
                } catch (PSException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process", ex);
                } catch (Exception ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "process", ex);
                    System.exit(1);
                }
                nb++;
            }
        }
        if (type.equals(UPDATE_FULL) || type.equals(UPDATE_RESOURCE)) {
            updateDVRResource();
        }

        if (type.equals(UPDATE_FULL) || type.equals(UPDATE_TIMESHEET)) {
            updateDVRTimesheet();
        }
    }

    private static void updateDVRResource() throws PSException {
        try {
            for (Resource r : listResource) {
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "Traitement de la Resource = " + r.getStringField("Name"));
                try {
                    r.lock();
                    Logger.getLogger(Run.class.getName()).log(Level.INFO, "VDD_RESOURCE_HEADER = " + VDD_RESOURCE_HEADER);

                    List vpbh = mSession.getDataViewRowList(VDD_RESOURCE_HEADER, r);
                    Iterator vpbhit = vpbh.iterator();
                    while (vpbhit.hasNext()) {
                        DataViewRow dvr_vpbh = (DataViewRow) vpbhit.next();
                        dvr_vpbh.remove();
                    }

                    List vpbl = mSession.getDataViewRowList(VDD_RESOURCE_LINE, r);
                    Iterator vpblit = vpbl.iterator();
                    while (vpblit.hasNext()) {
                        DataViewRow dvr_vpbl = (DataViewRow) vpblit.next();
                        dvr_vpbl.remove();
                    }

                    DataViewRow dvr_vpbh = new DataViewRow(VDD_RESOURCE_HEADER, r, DataViewRow.CREATE);

                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_FIRSTNAME, r.getStringField("First Name"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_ID, r.getStringField("ID"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_JOB, r.getStringField("Job Classification"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_LASTNAME, r.getStringField("Last Name"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_MAIL1, r.getStringField("Email Address 1"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_MAIL2, r.getStringField("Email Address 2"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_MAIL3, r.getStringField("Email Address 3"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_MIDDLENAME, r.getStringField("Middle Name"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_NAME, r.getStringField("Name"));
                    dvr_vpbh.setIntField(VDD_RESOURCE_HEADER_NUM, r.getIntField("#"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_ORGA, r.getStringField("Organization"));
                    dvr_vpbh.setStringField(VDD_RESOURCE_HEADER_SKILL, r.getStringListField("Skills").toString());

                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD1, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD2, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD3, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD4, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD5, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD6, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD7, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD8, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD9, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD10, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResourceString(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD11, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_STRING_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD12, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD13, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);
                    saveDVRResource(dvr_vpbh.getDoubleField(VDD_RESOURCE_HEADER_IDRESOURCE), r, VDD_RES_DISTR_FIELD14, VDD_RESOURCE_LINE, VDD_RESOURCE_LINE_EFFORT_VALUE);

                    r.save(true);
                } catch (LockException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRResource - for", ex);
                } catch (NullPointerException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRResource - for", ex);
                } catch (PSException ex) {
                    org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRResource - for", ex);
                }
            }
        } catch (InvalidDataException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRResource", ex);
            System.exit(1);
        } catch (LockException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRResource", ex);
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRResource", ex);
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRResource", ex);
            System.exit(1);
        }
    }

    private static void saveDVRResource(Double id, Resource res, String field_name, String vdd_name, String field_toSave) throws PSException {
        Global g = new Global();
        System.out.println("field_name = " + field_name);
        Calendar deb = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        deb.set(Calendar.MONTH, Calendar.JANUARY);
        end.set(Calendar.MONTH, Calendar.DECEMBER);
        deb.set(Calendar.DAY_OF_MONTH, 1);
        end.set(Calendar.DAY_OF_MONTH, 31);
        deb.add(Calendar.YEAR, MIN_YEAR);
        end.add(Calendar.YEAR, MAX_YEAR);
        try {
            List vpbh2 = mSession.getDataViewRowList(VDD_RES_DISTR, res);
            Iterator vpbhit2 = vpbh2.iterator();
            while (vpbhit2.hasNext()) {
                DataViewRow dvr_vpbh2 = (DataViewRow) vpbhit2.next();
                List<DatedData> lddd = dvr_vpbh2.getDatedData(field_name, DatedData.MONTH, deb.getTime(), end.getTime());
                Iterator ldddIt = lddd.iterator();
                while (ldddIt.hasNext()) {
                    try {
                        DoubleDatedData ddd = (DoubleDatedData) ldddIt.next();
                        Logger.getLogger(Run.class.getName()).log(Level.INFO, field_name + ": doubleDatedData = " + ddd.getData() + " <" + ddd.getStart() + "> à <" + ddd.getFinish() + ">");
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(ddd.getStart());
                        DataViewRow new_dvr = new DataViewRow(vdd_name, res, DataViewRow.CREATE);
                        new_dvr.setStringField(VDD_RESOURCE_LINE_FIELD, field_name);
                        new_dvr.setDoubleField(field_toSave, ddd.getData());
                        new_dvr.setStringField(VDD_RESOURCE_LINE_RESOURCE_NAME, res.getStringField("Name"));
                        new_dvr.setDoubleField(VDD_RESOURCE_LINE_IDRESOURCE, id);
                        new_dvr.setIntField(VDD_RESOURCE_LINE_MONTH, cal.get(Calendar.MONTH));
                        new_dvr.setIntField(VDD_RESOURCE_LINE_YEAR, cal.get(Calendar.YEAR));
                    } catch (PSException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResource - while", ex);
                    } catch (NullPointerException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResource - while", ex);
                    }
                }
            }
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResource", ex);
        } catch (NullPointerException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResource", ex);
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResource", ex);
            System.exit(1);
        }
    }

    private static void saveDVRResourceString(Double id, Resource res, String field_name, String vdd_name, String field_toSave) throws PSException {
        Global g = new Global();
        Calendar deb = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        int year = deb.get(Calendar.YEAR);
        year = year - 2;
        deb.set(Calendar.YEAR, year);
        year = year + 2;
        end.set(Calendar.YEAR, year);
        try {
            List vpbh2 = mSession.getDataViewRowList("FS_Ressource_Distr", res);
            Iterator vpbhit2 = vpbh2.iterator();
            while (vpbhit2.hasNext()) {
                DataViewRow dvr_vpbh2 = (DataViewRow) vpbhit2.next();
                List<DatedData> lddd = dvr_vpbh2.getDatedData(field_name, DatedData.MONTH, deb.getTime(), end.getTime());
                Iterator ldddIt = lddd.iterator();
                while (ldddIt.hasNext()) {
                    try {
                        StringDatedData ddd = (StringDatedData) ldddIt.next();
                        Logger.getLogger(Run.class.getName()).log(Level.INFO, "doubleDatedData = " + ddd.getData() + " <" + ddd.getStart() + "> à <" + ddd.getFinish() + ">");
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(ddd.getStart());
                        DataViewRow new_dvr = new DataViewRow(vdd_name, res, DataViewRow.CREATE);
                        new_dvr.setStringField(VDD_RESOURCE_LINE_FIELD, field_name);
                        new_dvr.setStringField(field_toSave, ddd.getData());
                        new_dvr.setStringField(VDD_RESOURCE_LINE_RESOURCE_NAME, res.getStringField("Name"));
                        new_dvr.setDoubleField(VDD_RESOURCE_LINE_IDRESOURCE, id);
                        new_dvr.setIntField(VDD_RESOURCE_LINE_MONTH, cal.get(Calendar.MONTH));
                        new_dvr.setIntField(VDD_RESOURCE_LINE_YEAR, cal.get(Calendar.YEAR));
                    } catch (PSException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResourceString - while", ex);
                    } catch (NullPointerException ex) {
                        org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResourceString - while", ex);
                    }
                }
            }
        } catch (PSException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResourceString", ex);
        } catch (NullPointerException ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResourceString", ex);
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "saveDVRResourceString", ex);
            System.exit(1);
        }
    }

    private static void updateDVRTimesheet() {
        Calendar cToday = Calendar.getInstance();

        Calendar cDebut = Calendar.getInstance();
        //cDebut.add(Calendar.DATE, NUMBER_OF_DAYS_BEFORE);
        cDebut.add(Calendar.MONTH, NUMBER_OF_MONTH_BEFORE);
        //cDebut.add(Calendar.YEAR, NUMBER_OF_YEARS_BEFORE);
        //cDebut.set(Calendar.DATE, 1);
        cDebut.set(Calendar.HOUR_OF_DAY, 2);
        cDebut.set(Calendar.MINUTE, 0);
        cDebut.set(Calendar.SECOND, 0);
        cDebut.set(Calendar.MILLISECOND, 0);

        Calendar cFin = Calendar.getInstance();
        //cFin.add(Calendar.DATE, NUMBER_OF_DAYS_BEFORE);
        cFin.add(Calendar.MONTH, NUMBER_OF_MONTH_BEFORE);
        //cFin.add(Calendar.YEAR, NUMBER_OF_YEARS_BEFORE);
        //cFin.set(Calendar.DATE, 1);
        cFin.set(Calendar.HOUR_OF_DAY, 22);
        cFin.set(Calendar.MINUTE, 59);
        cFin.set(Calendar.SECOND, 59);
        cFin.set(Calendar.MILLISECOND, 999);

        while (cToday.after(cFin)) {
            try {
                for (Resource r : listResource) {
                    Logger.getLogger(Run.class.getName()).log(Level.INFO, "Traitement de la Resource = " + r.getStringField("Name"));
                    try {
                        r.lock();
                        List vpbh = mSession.getDataViewRowList(VDD_ET_QV_TIMESHEET, r);
                        Iterator vpbhit = vpbh.iterator();
                        while (vpbhit.hasNext()) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            DataViewRow dvr_timesheet = (DataViewRow) vpbhit.next();
                            Date date_ts = dvr_timesheet.getDateField("date_timesheet");
                            if (sdf.format(date_ts).equals(sdf.format(cDebut.getTime()))) {
                                dvr_timesheet.remove();
                            }
                        }

                        Timesheet t;
                        t = mSession.getTimesheet(r, cDebut.getTime(), cFin.getTime());
                        Logger.getLogger(Run.class.getName()).log(Level.INFO, "Récupération de la Timesheet du " + cDebut.getTime() + " au " + cFin.getTime());
                        //Logger.getLogger(Run.class.getName()).log(Level.INFO, "Timesheet status: " + t.getStatusName());
                        List l = t.getTimesheetAssignmentList();
                        if (l == null) {
                            Logger.getLogger(Run.class.getName()).log(Level.INFO, "  No assignments");
                        } else {
                            Iterator it = l.iterator();
                            while (it.hasNext()) {
                                TimesheetAssignment ta = (TimesheetAssignment) it.next();
                                traitementTimesheetAssignment(ta, r, cDebut);
                            }
                        }
                        r.save(true);
                    } catch (PSException ex) {
                        Logger.getLogger(Run.class.getName()).log(Level.ERROR, r.getStringField("Name") + " est verrouillé traitement de la ressource suivante.");
                    }
                }
            } catch (PSException ex) {
                Logger.getLogger(Run.class.getName()).log(Level.ERROR, "updateDVRTimesheet", ex);
            }
            cDebut.add(Calendar.DATE, 1); //Jour suivant
            cFin.add(Calendar.DATE, 1);
        }
    }

    private static void traitementTimesheetAssignment(TimesheetAssignment ta, Resource res, Calendar cal) {
        java.util.List list;
        Iterator it;
        try {
            double sommeActuals = 0.0;
            list = ta.getDatedData("Actuals");
            it = (list.iterator());
            while (it.hasNext()) {
                DoubleDatedData actuals = (DoubleDatedData) it.next();
                //System.out.println("    Actuals for " + DateFormat.getDateInstance().format(actuals.getStart()) + " to " + DateFormat.getDateInstance().format(actuals.getFinish()) + ":");
                //System.out.println("       > " + actuals.getData() + "h");
                sommeActuals = sommeActuals + actuals.getData();
            }
            if (sommeActuals > 0) {
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "----------------- Assignment -------------------");
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "Assignment status: " + ta.getStatus() + " (" + ta.getStatusName() + ")");
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  ID: " + ta.getStringField("ID"));
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  Name: " + ta.getStringField("Name"));
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  Task IID: " + ta.getDoubleField("Task IID"));
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  Project ID: " + ta.getStringField("Project ID"));
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  Project IID: " + ta.getDoubleField("Project IID"));
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  Project Name: " + ta.getStringField("Project Name"));
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  % Complete: " + ta.getDoubleField("% Complete"));
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  Duration: " + ta.getDoubleField("Duration"));
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  Actual Effort: " + sommeActuals);
                Logger.getLogger(Run.class.getName()).log(Level.INFO, "  Remaining Effort: " + ta.getDoubleField("Remaining Effort"));
                DataViewRow new_dvr = new DataViewRow(VDD_ET_QV_TIMESHEET, res, DataViewRow.CREATE);
                new_dvr.setStringField("project_name", ta.getStringField("Project Name"));
                new_dvr.setStringField("project_id", ta.getStringField("Project ID"));
                new_dvr.setStringField("task_name", ta.getStringField("Name"));
                new_dvr.setStringField("task_id", ta.getStringField("ID"));
                new_dvr.setStringField("resource_name", res.getStringField("Name"));
                new_dvr.setDoubleField("percent_complete", ta.getDoubleField("% Complete"));
                new_dvr.setDoubleField("duration", ta.getDoubleField("Duration"));
                new_dvr.setDoubleField("actual_effort", sommeActuals);
                new_dvr.setDoubleField("remaining_effort", ta.getDoubleField("Remaining Effort"));
                new_dvr.setDateField("date_timesheet", cal.getTime());
            }
        } catch (PSException e) {
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, "An exception occurred during Timesheet API testing:");
            org.apache.log4j.Logger.getLogger(Run.class.getName()).log(Level.ERROR, e.getLocalizedMessage());
        }
    }

}
