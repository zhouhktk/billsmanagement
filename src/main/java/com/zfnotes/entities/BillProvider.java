package com.zfnotes.entities;

/**
 * 对账单的再封装
 */
public class BillProvider extends Bill{
    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
