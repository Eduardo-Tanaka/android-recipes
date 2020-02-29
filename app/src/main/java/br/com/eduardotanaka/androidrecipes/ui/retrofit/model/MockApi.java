package br.com.eduardotanaka.androidrecipes.ui.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MockApi implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("name")
    private String name;
    @SerializedName("avatar")
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeString(this.name);
        dest.writeString(this.avatar);
    }

    public MockApi() {
    }

    protected MockApi(Parcel in) {
        this.id = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.name = in.readString();
        this.avatar = in.readString();
    }

    public static final Parcelable.Creator<MockApi> CREATOR = new Parcelable.Creator<MockApi>() {
        @Override
        public MockApi createFromParcel(Parcel source) {
            return new MockApi(source);
        }

        @Override
        public MockApi[] newArray(int size) {
            return new MockApi[size];
        }
    };

    @Override
    public String toString() {
        return "MockApi{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
