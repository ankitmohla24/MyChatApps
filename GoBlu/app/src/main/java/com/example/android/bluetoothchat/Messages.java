package com.example.android.bluetoothchat;

public class Messages
{
    boolean isMine;
    boolean isSticker;
    String message;
    
    public Messages(final String message, final boolean isMine, final boolean isSticker) {
        super();
        this.message = message;
        this.isMine = isMine;
        this.isSticker = isSticker;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public boolean isMine() {
        return this.isMine;
    }
    
    public boolean isSticker() {
        return this.isSticker;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public void setMine(final boolean isMine) {
        this.isMine = isMine;
    }
}
