package com.example.a2doparcial.Presentacion;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.a2doparcial.Dato.DProducto;
import com.example.a2doparcial.Negocio.NProducto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CatalogoFacade {

    private Context context;
    private Activity activity;
    private NProducto np;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION =1;

    public CatalogoFacade(Context context,Activity activity){
        this.context=context;
        this.activity=activity;
    }



    private void crearPDF() {
        np=new NProducto(activity);
        Document document = new Document();
        String dirreccion = context.getApplicationContext().getExternalFilesDir(null).toString() + "/catalogo.pdf";
        File file = new File(dirreccion);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();
            Font fuente = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph parrafoInicial = new Paragraph("Catálogo de Productos", fuente);
            parrafoInicial.setAlignment(Element.ALIGN_CENTER);
            document.add( parrafoInicial );
            ArrayList<DProducto> productos = np.getListaProductos();
            PdfPTable tabla = new PdfPTable(3);
            for (DProducto producto : productos) {
                Image imagen = Image.getInstance(producto.getImagen());
                imagen.scaleAbsolute(20, 10);
                tabla.addCell( imagen );
                tabla.addCell("Nombre: " + producto.getNombre());
                tabla.addCell("Precio Bs: " + Double.toString(producto.getPrecio()));
                // tabla.addCell("Categoria: " + categoria.getNombre());
            }
            document.add(tabla);
            document.add(new Paragraph(" "));
            tabla.deleteBodyRows();
            document.close();
            fileOutputStream.close();
            sharePDF(file);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sharePDF(File file) {
        Uri url = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".fileprovider", file);
        Intent compartir = new Intent(Intent.ACTION_SEND);
        compartir.setType("application/pdf");
        compartir.putExtra(Intent.EXTRA_STREAM, url);
        compartir.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        compartir.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(Intent.createChooser(compartir, "Catálogo de Productos"));
    }


    public void compartirPDF() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context.getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                crearPDF();
            } else {
                ActivityCompat.requestPermissions(
                        activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
            }
        } else {
            crearPDF();
        }
    }
}
