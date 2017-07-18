package org.opensingular.formsamples.crud.types;

import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoPackage;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SPackage;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;

//@formatter:off
@SInfoPackage(name = MyPackage.NAME)
public class MyPackage extends SPackage {
    public static final String NAME = "myPackage";
    public MyTypeForm          form;
    public MyTypeAddress       address;
    public MyTypeCity          city;

    @Override
    protected void onLoadPackage(PackageBuilder pb) {
        super.onLoadPackage(pb);
        (city    = pb.createType(MyTypeCity   .class)).asAtr().label("City");
        (address = pb.createType(MyTypeAddress.class)).asAtr().label("Address");
        (form    = pb.createType(MyTypeForm   .class)).asAtr().label("Form");
    }

    @SInfoType(spackage = MyPackage.class)
    public static class MyTypeForm extends STypeComposite<SIComposite> {
        public STypeString   name;
        public STypeInteger  age;
        public MyTypeAddress homeAddress;
        public MyTypeAddress workAddress;

        @Override
        protected void onLoadType(TypeBuilder tb) {
            (name        = addFieldString ("name"                     , true )).asAtr().label("Name"        ).help("Full name");
            (age         = addFieldInteger("age"                             )).asAtr().label("Age"         );
            (homeAddress = addField       ("homeAddress", MyTypeAddress.class)).asAtr().label("Home address").help("Residential address");
            (workAddress = addField       ("workAddress", MyTypeAddress.class)).asAtr().label("Work adress" ).help("Commercial address");
            age.asAtrAnnotation().setAnnotated();
        }
    }

    @SInfoType(spackage = MyPackage.class)
    public static class MyTypeAddress extends STypeComposite<SIComposite> {
        public STypeString  streetAddress;
        public STypeInteger zipCode;
        public MyTypeCity   city;

        @Override
        protected void onLoadType(TypeBuilder tb) {
            (streetAddress = addFieldString ("streetAddress"          )).asAtr().label("Street address");
            (zipCode       = addFieldInteger("zipCode"                )).asAtr().label("Zip code"      );
            (city          = addField       ("city" , MyTypeCity.class)).asAtr().label("City"          );
            asAtrAnnotation().setAnnotated();
        }
    }

    @SInfoType(spackage = MyPackage.class)
    public static class MyTypeCity extends STypeComposite<SIComposite> {
        public STypeString name;
        public STypeString country;

        @Override
        protected void onLoadType(TypeBuilder tb) {
            (name    = addFieldString("name"   )).asAtr().label("Name"   );
            (country = addFieldString("country")).asAtr().label("Country");
            asAtrAnnotation().setAnnotated();
        }
    }
}
//@formatter:on