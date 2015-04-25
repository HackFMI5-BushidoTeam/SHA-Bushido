package com.hackfmi.bushidoserver;

import android.annotation.TargetApi;
import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.Locale;
import java.net.*;

/**
 * Created by Vlex on 4/25/2015.
 */
public class BeamData extends Activity {
    private NfcAdapter mNfcAdapter;
    private TextView mTextView;
    private NdefMessage mNdefMessage;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    @Override
    public void onCreate(Bundle savedState) {

        super.onCreate(savedState);

        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.tv);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter != null) {
            mTextView.setText("Tap to beam to another NFC device");
        } else {
            mTextView.setText("This phone is not NFC enabled.");
        }

        // create an NDEF message with two records of plain text type
        mNdefMessage = new NdefMessage(
                new NdefRecord[] {
                        createNewTextRecord("First sample NDEF text record", Locale.ENGLISH, true),
                        createNewTextRecord("Second sample NDEF text record", Locale.ENGLISH, true) });
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static NdefRecord createNewTextRecord(String text, Locale locale, boolean encodeInUtf8) {
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));

        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
        byte[] textBytes = text.getBytes(utfEncoding);

        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        char status = (char)(utfBit + langBytes.length);

        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte)status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);

        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    @Override
    public void onResume() {
        super.onResume();

        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundNdefPush(this, mNdefMessage);
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    @Override
    public void onPause() {
        super.onPause();

        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundNdefPush(this);
    }
}
