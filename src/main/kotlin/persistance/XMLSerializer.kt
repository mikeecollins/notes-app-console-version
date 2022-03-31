package persistance


import java.io.File
import kotlin.Throws
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import controllers.NoteAPI
import models.Note
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception


private var populatedNotes: NoteAPI? = NoteAPI(XMLSerializer(File("notes.xml")))
private var emptyNotes: NoteAPI? = NoteAPI(XMLSerializer(File("notes.xml")))

class XMLSerializer(private val file: File) : Serializer {

    @Throws(Exception::class)
    override fun read(): Any {
        val xStream = XStream(DomDriver())
        xStream.allowTypes(arrayOf(Note::class.java))
        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject() as Any
        inputStream.close()
        return obj
    }

    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(DomDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }


}
