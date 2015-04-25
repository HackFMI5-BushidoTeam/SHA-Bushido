package com.hackfmi.bushidoserver;

import android.nfc.NdefMessage;
import android.nfc.NfcEvent;

import org.ndeftools.Message;
import org.ndeftools.MimeRecord;
import org.ndeftools.externaltype.AndroidApplicationRecord;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;

/**
 * Created by Vlex on 4/25/2015.
 */
public class Beamer extends NfcBeamWriterActivity {

    public Beamer(String ssid, String pass, String publicKey, RSA keypair) throws UnsupportedEncodingException {
        AndroidApplicationRecord aar = new AndroidApplicationRecord();
        aar.setPackageName("com.hackfmi.bushidoclient");
        String dataToBePassed = ssid+"&"+pass+"&"+publicKey;
        MimeRecord mimeRecord = new MimeRecord();
        mimeRecord.setMimeType("text/plain");
        mimeRecord.setData(dataToBePassed.getBytes("UTF-8"));
        Message message = new Message(); //  org.ndeftools.Message
        message.add(aar);
        message.add(mimeRecord);
    }

    @Override
    protected void onNfcPushStateEnabled() {

    }

    @Override
    protected void onNfcPushStateDisabled() {

    }

    @Override
    protected void onNfcPushStateChange(boolean enabled) {

    }

    @Override
    protected void onNdefPushCompleted() {

    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        return null;
    }

    @Override
    protected void readNdefMessage(Message message) {

    }

    @Override
    protected void readEmptyNdefMessage() {

    }

    @Override
    protected void readNonNdefMessage() {

    }

    @Override
    protected void onNfcStateEnabled() {

    }

    @Override
    protected void onNfcStateDisabled() {

    }

    @Override
    protected void onNfcStateChange(boolean enabled) {

    }

    @Override
    protected void onNfcFeatureNotFound() {

    }

    @Override
    protected void onTagLost() {

    }
}
