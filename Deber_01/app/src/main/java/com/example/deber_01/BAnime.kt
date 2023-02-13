import android.os.Parcel
import android.os.Parcelable

class BAnime (
    var idAnime: Int,
    var nombreAnime: String,
    var anoDeLanzamientoAnime: String,
    var generoAnime: String,
    var estudioAnime: String,
): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return "${nombreAnime} - ${anoDeLanzamientoAnime} - ${generoAnime} - ${estudioAnime}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idAnime)
        parcel.writeString(nombreAnime)
        parcel.writeString(anoDeLanzamientoAnime)
        parcel.writeString(generoAnime)
        parcel.writeString(estudioAnime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BAnime> {
        override fun createFromParcel(parcel: Parcel): BAnime {
            return BAnime(parcel)
        }

        override fun newArray(size: Int): Array<BAnime?> {
            return arrayOfNulls(size)
        }
    }

    fun getID(): Int{
        return idAnime
    }

}
