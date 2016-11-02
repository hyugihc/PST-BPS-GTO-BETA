package com.wordpress.hyugihc.pstbeta;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.hyugihc.pstbeta.database.Feedback;
import com.wordpress.hyugihc.pstbeta.database.FeedbackContract;
import com.wordpress.hyugihc.pstbeta.database.FeedbackDbHelper;

import java.text.DecimalFormat;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReportFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_YEAR = "section_number";
    TextView jansa, janpa, janua, janta, febsa, febpa, febua, febta, marchsa, marchpa, marchua, marchta;
    TextView aprilsa, aprilpa, aprilua, aprilta, maysa, maypa, mayua, mayta, junesa, junepa, juneua, juneta;
    TextView julysa, julypa, julyua, julyta, augsa, augpa, augua, augta, septsa, septpa, septua, septta;
    TextView octsa, octpa, octua, octta, novsa, novpa, novua, novta, dessa, despa, desua, desta;
    TextView totsa, totpa, totua, totta;
    TextView reportTitle;
    TextView jansp, janpp, janup, febsp, febpp, febup, marchsp, marchpp, marchup;
    TextView aprilsp, aprilpp, aprilup, maysp, maypp, mayup, junesp, junepp, juneup;
    TextView julysp, julypp, julyup, augsp, augpp, augup, septsp, septpp, septup;
    TextView octsp, octpp, octup, novsp, novpp, novup, dessp, despp, desup;
    TextView totsp, totpp, totup;
    DecimalFormat decimalFormat;
    int year;
    View rootView;
    public ReportFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ReportFragment newInstance(int sectionNumber) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_YEAR, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        reportTitle = (TextView) rootView.findViewById(R.id.report_title);
        jansp = (TextView) rootView.findViewById(R.id.jansp);
        janpp = (TextView) rootView.findViewById(R.id.janpp);
        janup = (TextView) rootView.findViewById(R.id.janup);
        febsp = (TextView) rootView.findViewById(R.id.febsp);
        febpp = (TextView) rootView.findViewById(R.id.febpp);
        febup = (TextView) rootView.findViewById(R.id.febup);
        marchsp = (TextView) rootView.findViewById(R.id.marchsp);
        marchpp = (TextView) rootView.findViewById(R.id.marchpp);
        marchup = (TextView) rootView.findViewById(R.id.marchup);
        aprilsp = (TextView) rootView.findViewById(R.id.aprilsp);
        aprilpp = (TextView) rootView.findViewById(R.id.aprilpp);
        aprilup = (TextView) rootView.findViewById(R.id.aprilup);
        maysp = (TextView) rootView.findViewById(R.id.maysp);
        maypp = (TextView) rootView.findViewById(R.id.maypp);
        mayup = (TextView) rootView.findViewById(R.id.mayup);
        junesp = (TextView) rootView.findViewById(R.id.junesp);
        junepp = (TextView) rootView.findViewById(R.id.junepp);
        juneup = (TextView) rootView.findViewById(R.id.juneup);
        julysp = (TextView) rootView.findViewById(R.id.julysp);
        julypp = (TextView) rootView.findViewById(R.id.julypp);
        julyup = (TextView) rootView.findViewById(R.id.julyup);
        augsp = (TextView) rootView.findViewById(R.id.augsp);
        augpp = (TextView) rootView.findViewById(R.id.augpp);
        augup = (TextView) rootView.findViewById(R.id.augup);
        septsp = (TextView) rootView.findViewById(R.id.septsp);
        septpp = (TextView) rootView.findViewById(R.id.septpp);
        septup = (TextView) rootView.findViewById(R.id.septup);
        octsp = (TextView) rootView.findViewById(R.id.octsp);
        octpp = (TextView) rootView.findViewById(R.id.octpp);
        octup = (TextView) rootView.findViewById(R.id.octup);
        novsp = (TextView) rootView.findViewById(R.id.novsp);
        novpp = (TextView) rootView.findViewById(R.id.novpp);
        novup = (TextView) rootView.findViewById(R.id.novup);
        dessp = (TextView) rootView.findViewById(R.id.dessp);
        despp = (TextView) rootView.findViewById(R.id.despp);
        desup = (TextView) rootView.findViewById(R.id.desup);
        totsp = (TextView) rootView.findViewById(R.id.totsp);
        totpp = (TextView) rootView.findViewById(R.id.totpp);
        totup = (TextView) rootView.findViewById(R.id.totup);
        jansa = (TextView) rootView.findViewById(R.id.jansa);
        janpa = (TextView) rootView.findViewById(R.id.janpa);
        janua = (TextView) rootView.findViewById(R.id.janua);
        janta = (TextView) rootView.findViewById(R.id.janta);
        febsa = (TextView) rootView.findViewById(R.id.febsa);
        febpa = (TextView) rootView.findViewById(R.id.febpa);
        febua = (TextView) rootView.findViewById(R.id.febua);
        febta = (TextView) rootView.findViewById(R.id.febta);
        marchsa = (TextView) rootView.findViewById(R.id.marchsa);
        marchpa = (TextView) rootView.findViewById(R.id.marchpa);
        marchua = (TextView) rootView.findViewById(R.id.marchua);
        marchta = (TextView) rootView.findViewById(R.id.marchta);
        aprilsa = (TextView) rootView.findViewById(R.id.aprilsa);
        aprilpa = (TextView) rootView.findViewById(R.id.aprilpa);
        aprilua = (TextView) rootView.findViewById(R.id.aprilua);
        aprilta = (TextView) rootView.findViewById(R.id.aprilta);
        maysa = (TextView) rootView.findViewById(R.id.maysa);
        maypa = (TextView) rootView.findViewById(R.id.maypa);
        mayua = (TextView) rootView.findViewById(R.id.mayua);
        mayta = (TextView) rootView.findViewById(R.id.mayta);
        junesa = (TextView) rootView.findViewById(R.id.junesa);
        junepa = (TextView) rootView.findViewById(R.id.junepa);
        juneua = (TextView) rootView.findViewById(R.id.juneua);
        juneta = (TextView) rootView.findViewById(R.id.juneta);
        julysa = (TextView) rootView.findViewById(R.id.julysa);
        julypa = (TextView) rootView.findViewById(R.id.julypa);
        julyua = (TextView) rootView.findViewById(R.id.julyua);
        julyta = (TextView) rootView.findViewById(R.id.julyta);
        augsa = (TextView) rootView.findViewById(R.id.augsa);
        augpa = (TextView) rootView.findViewById(R.id.augpa);
        augua = (TextView) rootView.findViewById(R.id.augua);
        augta = (TextView) rootView.findViewById(R.id.augta);
        septsa = (TextView) rootView.findViewById(R.id.septsa);
        septpa = (TextView) rootView.findViewById(R.id.septpa);
        septua = (TextView) rootView.findViewById(R.id.septua);
        septta = (TextView) rootView.findViewById(R.id.septta);
        octsa = (TextView) rootView.findViewById(R.id.octsa);
        octpa = (TextView) rootView.findViewById(R.id.octpa);
        octua = (TextView) rootView.findViewById(R.id.octua);
        octta = (TextView) rootView.findViewById(R.id.octta);
        novsa = (TextView) rootView.findViewById(R.id.novsa);
        novpa = (TextView) rootView.findViewById(R.id.novpa);
        novua = (TextView) rootView.findViewById(R.id.novua);
        novta = (TextView) rootView.findViewById(R.id.novta);
        dessa = (TextView) rootView.findViewById(R.id.dessa);
        despa = (TextView) rootView.findViewById(R.id.despa);
        desua = (TextView) rootView.findViewById(R.id.desua);
        desta = (TextView) rootView.findViewById(R.id.desta);
    }

    public void refreshTable() {

        decimalFormat = new DecimalFormat("#.##");
        FeedbackDbHelper feedbackDbHelper = new FeedbackDbHelper(getActivity());
        SQLiteDatabase db = feedbackDbHelper.getReadableDatabase();


        //januari
        List<Feedback> feedbackListJan = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-01-01", Integer.toString(year) + "-01-31");
        int jansc = 0;
        int januc = 0;
        int janpc = 0;
        for (Feedback feedback : feedbackListJan) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                janpc = janpc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                jansc = jansc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                januc = januc + 1;
        }
        jansa.setText(Integer.toString(jansc));
        janpa.setText(Integer.toString(janpc));
        janua.setText(Integer.toString(januc));
        double jantot = jansc + januc + janpc;
        if (jantot != 0) {
            jansp.setText(decimalFormat.format(jansc / jantot * 100));
            janpp.setText(decimalFormat.format(janpc / jantot * 100));
            janup.setText(decimalFormat.format(januc / jantot * 100));
        }
        janta.setText(Integer.toString(jansc + januc + janpc));

        //february
        List<Feedback> feedbackListFeb = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-02-01", Integer.toString(year) + "-02-31");

        int febsc = 0;
        int febuc = 0;
        int febpc = 0;
        for (Feedback feedback : feedbackListFeb) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                febpc = febpc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                febsc = febsc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                febuc = febuc + 1;
        }
        febsa.setText(Integer.toString(febsc));
        febpa.setText(Integer.toString(febpc));
        febua.setText(Integer.toString(febuc));
        int febtot = febsc + febuc + febpc;
        if (febtot != 0) {
            febsp.setText(decimalFormat.format(febsc / febtot * 100));
            febpp.setText(decimalFormat.format(febpc / febtot * 100));
            febup.setText(decimalFormat.format(febuc / febtot * 100));
        }
        febta.setText(Integer.toString(febtot));

        //maret
        List<Feedback> feedbackListMarch = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-03-01", Integer.toString(year) + "-03-31");

        int marchsc = 0;
        int marchuc = 0;
        int marchpc = 0;
        for (Feedback feedback : feedbackListMarch) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                marchpc = marchpc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                marchsc = marchsc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                marchuc = marchuc + 1;
        }
        marchsa.setText(Integer.toString(marchsc));
        marchpa.setText(Integer.toString(marchpc));
        marchua.setText(Integer.toString(marchuc));
        double marchtot = marchsc + marchuc + marchpc;

        if (marchtot != 0) {
            marchsp.setText(decimalFormat.format(marchsc / marchtot * 100));
            marchpp.setText(decimalFormat.format(marchpc / marchtot * 100));
            marchup.setText(decimalFormat.format(marchuc / marchtot * 100));
        }
        marchta.setText(Integer.toString(marchsc + marchuc + marchpc));

        //april
        List<Feedback> feedbackListApril = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-04-01", Integer.toString(year) + "-04-31");

        int aprilsc = 0;
        int apriluc = 0;
        int aprilpc = 0;
        for (Feedback feedback : feedbackListApril) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                aprilpc = aprilpc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                aprilsc = aprilsc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                apriluc = apriluc + 1;
        }
        aprilsa.setText(Integer.toString(aprilsc));
        aprilpa.setText(Integer.toString(aprilpc));
        aprilua.setText(Integer.toString(apriluc));
        double apriltot = aprilsc + apriluc + aprilpc;
        if (apriltot != 0) {
            aprilsp.setText(decimalFormat.format(aprilsc / apriltot * 100));
            aprilpp.setText(decimalFormat.format(aprilpc / apriltot * 100));
            aprilup.setText(decimalFormat.format(apriluc / apriltot * 100));
        }
        aprilta.setText(Integer.toString(aprilsc + apriluc + aprilpc));

        //may
        List<Feedback> feedbackListMay = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-05-01", Integer.toString(year) + "-05-31");

        int maysc = 0;
        int mayuc = 0;
        int maypc = 0;
        for (Feedback feedback : feedbackListMay) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                maypc = maypc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                maysc = maysc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                mayuc = mayuc + 1;
        }
        maysa.setText(Integer.toString(maysc));
        maypa.setText(Integer.toString(maypc));
        mayua.setText(Integer.toString(mayuc));
        double maytot = maysc + mayuc + maypc;
        if (maytot != 0) {
            maysp.setText(decimalFormat.format(maysc / maytot * 100));
            maypp.setText(decimalFormat.format(maypc / maytot * 100));
            mayup.setText(decimalFormat.format(mayuc / maytot * 100));
        }
        mayta.setText(Integer.toString(maysc + mayuc + maypc));

        //june

        List<Feedback> feedbackListJune = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-06-01", Integer.toString(year) + "-06-31");


        int junesc = 0;
        int juneuc = 0;
        int junepc = 0;
        for (Feedback feedback : feedbackListJune) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                junepc = junepc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                junesc = junesc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                juneuc = juneuc + 1;
        }
        junesa.setText(Integer.toString(junesc));
        junepa.setText(Integer.toString(junepc));
        juneua.setText(Integer.toString(juneuc));
        double junetot = junesc + juneuc + junepc;
        if (junetot != 0) {
            junesp.setText(decimalFormat.format(junesc / junetot * 100));
            junepp.setText(decimalFormat.format(junepc / junetot * 100));
            juneup.setText(decimalFormat.format(juneuc / junetot * 100));
        }
        juneta.setText(Integer.toString(junesc + juneuc + junepc));

        //july

        List<Feedback> feedbackListJuly = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-07-01", Integer.toString(year) + "-07-31");

        int julysc = 0;
        int julyuc = 0;
        int julypc = 0;
        for (Feedback feedback : feedbackListJuly) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                julypc = julypc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                julysc = julysc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                julyuc = julyuc + 1;
        }
        julysa.setText(Integer.toString(julysc));
        julypa.setText(Integer.toString(julypc));
        julyua.setText(Integer.toString(julyuc));
        double julytot = julysc + julyuc + julypc;
        if (julytot != 0) {
            julysp.setText(decimalFormat.format(julysc / julytot * 100));
            julypp.setText(decimalFormat.format(julypc / julytot * 100));
            julyup.setText(decimalFormat.format(julyuc / julytot * 100));
        }
        julyta.setText(Integer.toString(julysc + julyuc + julypc));

        //aug

        List<Feedback> feedbackListAug = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-08-01", Integer.toString(year) + "-08-31");

        int augsc = 0;
        int auguc = 0;
        int augpc = 0;
        for (Feedback feedback : feedbackListAug) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                augpc = augpc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                augsc = augsc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                auguc = auguc + 1;
        }
        augsa.setText(Integer.toString(augsc));
        augpa.setText(Integer.toString(augpc));
        augua.setText(Integer.toString(auguc));
        double augtot = augsc + auguc + augpc;
        if (augtot != 0) {
            augsp.setText(decimalFormat.format(augsc / augtot * 100));
            augpp.setText(decimalFormat.format(augpc / augtot * 100));
            augup.setText(decimalFormat.format(auguc / augtot * 100));
        }
        augta.setText(Integer.toString(augsc + auguc + augpc));

        //sept

        List<Feedback> feedbackListsept = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-09-01", Integer.toString(year) + "-09-31");

        int septsc = 0;
        int septuc = 0;
        int septpc = 0;
        for (Feedback feedback : feedbackListsept) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                septpc = septpc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                septsc = septsc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                septuc = septuc + 1;
        }
        septsa.setText(Integer.toString(septsc));
        septpa.setText(Integer.toString(septpc));
        septua.setText(Integer.toString(septuc));
        double septtot = septsc + septuc + septpc;
        if (septtot != 0) {
            septsp.setText(decimalFormat.format(septsc / septtot * 100));
            septpp.setText(decimalFormat.format(septpc / septtot * 100));
            septup.setText(decimalFormat.format(septuc / septtot * 100));
        }
        septta.setText(Integer.toString(septsc + septuc + septpc));

        //oct

        List<Feedback> feedbackListoct = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-10-01", Integer.toString(year) + "-10-31");

        int octsc = 0;
        int octuc = 0;
        int octpc = 0;
        for (Feedback feedback : feedbackListoct) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                octpc = octpc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                octsc = octsc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                octuc = octuc + 1;
        }
        octsa.setText(Integer.toString(octsc));
        octpa.setText(Integer.toString(octpc));
        octua.setText(Integer.toString(octuc));
        double octtot = octsc + octuc + octpc;
        if (octtot != 0) {
            octsp.setText(decimalFormat.format(octsc / octtot * 100));
            octpp.setText(decimalFormat.format(octpc / octtot * 100));
            octup.setText(decimalFormat.format(octuc / octtot * 100));
        }
        octta.setText(Integer.toString(octsc + octuc + octpc));

        //nov

        List<Feedback> feedbackListNov = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-11-01", Integer.toString(year) + "-11-31");

        int novsc = 0;
        int novuc = 0;
        int novpc = 0;
        for (Feedback feedback : feedbackListNov) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                novpc = novpc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                novsc = novsc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                novuc = novuc + 1;
        }
        novsa.setText(Integer.toString(novsc));
        novpa.setText(Integer.toString(novpc));
        novua.setText(Integer.toString(novuc));
        double novtot = novsc + novuc + novpc;
        if (novtot != 0) {
            novsp.setText(decimalFormat.format(novsc / novtot * 100));
            novpp.setText(decimalFormat.format(novpc / novtot * 100));
            novup.setText(decimalFormat.format(novuc / novtot * 100));
        }
        novta.setText(Integer.toString(novsc + novuc + novpc));

        //des

        List<Feedback> feedbackListdes = feedbackDbHelper.getFeedbackByRangeDate(Integer.toString(year) + "-12-01", Integer.toString(year) + "-12-31");

        int dessc = 0;
        int desuc = 0;
        int despc = 0;
        for (Feedback feedback : feedbackListdes) {
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS))
                despc = despc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS))
                dessc = dessc + 1;
            if (feedback.getResponse().equals(FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS))
                desuc = desuc + 1;
        }
        dessa.setText(Integer.toString(dessc));
        despa.setText(Integer.toString(despc));
        desua.setText(Integer.toString(desuc));
        double destot = dessc + desuc + despc;
        if (destot != 0) {
            dessp.setText(decimalFormat.format(dessc / destot * 100));
            despp.setText(decimalFormat.format(despc / destot * 100));
            desup.setText(decimalFormat.format(desuc / destot * 100));
        }
        desta.setText(Integer.toString(dessc + desuc + despc));

        //total
        totsa = (TextView) rootView.findViewById(R.id.totsa);
        totpa = (TextView) rootView.findViewById(R.id.totpa);
        totua = (TextView) rootView.findViewById(R.id.totua);
        totta = (TextView) rootView.findViewById(R.id.totta);
        int sc = jansc + febsc + marchsc + aprilsc + maysc + junesc + julysc + augsc + septsc + octsc + novsc + dessc;
        int pc = janpc + febpc + marchpc + aprilpc + maypc + junepc + julypc + augpc + septpc + octpc + novpc + despc;
        int uc = januc + febuc + marchuc + apriluc + mayuc + juneuc + julyuc + auguc + septuc + octuc + novuc + despc;
        totsa.setText(Integer.toString(sc));
        totpa.setText(Integer.toString(pc));
        totua.setText(Integer.toString(uc));
        double tottot = sc + pc + uc;
        if (tottot != 0) {
            totsp.setText(decimalFormat.format(sc / tottot * 100));
            totpp.setText(decimalFormat.format(pc / tottot * 100));
            totup.setText(decimalFormat.format(uc / tottot * 100));
        }
        totta.setText(Integer.toString(sc + pc + uc));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  setHasOptionsMenu(true);
        rootView = inflater.inflate(R.layout.fragment_report, container, false);
        initView();
        year = getArguments().getInt(ARG_YEAR);
        reportTitle.setText("REKAP KEPUASAN PENGGUNA TAHUN " + Integer.toString(year));
        refreshTable();
        return rootView;


    }
}