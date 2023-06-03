package a1_bi12_099;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/*
@overview 
Student are people which study in a school or a university
Have 2 specific types: UndergradStudent and PostgradStudent

@attributes 
id    Integer
name  String
phoneNum String
address String   

@object A typical Student is c=<m,n,p,q>, where id(m), name(n), phoneNum(p), address(q).

@abstract_properties
mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\ max(id)=pow(10, 9) 
mutable(name)=true /\ optional(name)=false /\ length(name)=50
mutable(phoneNum)=true /\ optional(phoneNum)=false /\ length(phoneNum)=10
mutable(address)=true /\ optional(address)=false /\ length(address)=100
*/

public class Student implements Comparable {
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 1000000000;
    private static final int LENGTH_NAME = 50;
    private static final int LENGTH_PHONE_NUM = 10;
    private static final int LENGTH_ADDRESS = 100;

    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
    private int id;

    @DomainConstraint(type = "String", optional = false, length = LENGTH_NAME)
    private String name;

    @DomainConstraint(type = "String", optional = false, length = LENGTH_PHONE_NUM)
    private String phoneNum;

    @DomainConstraint(type = "String", optional = false, length = LENGTH_ADDRESS)
    private String address;
    
    /*
    @effects <pre> 
    if id, name, phoneNum, address are valid
        initialise this as <id,name,phoneNum,address>
    else
        initialise this as <> and inform error</pre>
    //@DOpt(type=OptType.Constructor)
    */
    public Student(@AttrRef("id") int id, @AttrRef("name") String name,
        @AttrRef("phoneNum") String phoneNum, @AttrRef("address") String address) throws NotPossibleException {
    // if id, name, phoneNum, address are valid
        if (!validateId(id)) {
            throw new NotPossibleException("Student.init: Invalid Student id: " + id);
        }
    
        if (!validateName(name)) {
            throw new NotPossibleException("Student.init: Invalid Student name: " + name);
        }

        if (!validatePhoneNum(phoneNum)) {
			throw new NotPossibleException("Student.init: Invalid phone number: " + phoneNum);
		}

		if (!validateAddress(address)) {
			throw new NotPossibleException("Student.init: Invalid address: " + address);
		} 
    
        // initialise this as <id,name,phoneNum,address>
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
    }
    
        @DOpt(type=OptType.Mutator) 
    @AttrRef("name")
    public boolean setName(String name) {
        if (validateName(name)) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }
    
    @DOpt(type=OptType.Mutator) 
    @AttrRef("phoneNum")
    public boolean setPhoneNum(String phoneNum) {
        if (validatePhoneNum(phoneNum)) {
            this.phoneNum = phoneNum;
            return true;
        } else {
            return false;
        }
    }
    
    @DOpt(type=OptType.Mutator) 
    @AttrRef("address")
    public boolean setAddress(String address) {
        if (validateAddress(address)) {
            this.address = address;
            return true;
        } else {
            return false;
        }
    }
    
    // @effects return <tt>id</tt>
    @DOpt(type=OptType.Observer) @AttrRef("id")
    public int getId() {
        return this.id;
    }
    
    // @effects return <tt>name</tt>
    @DOpt(type=OptType.Observer) @AttrRef("name")
    public String getName() {
        return this.name;
    }
    
    // @effects return <tt>phoneNum</tt>
    @DOpt(type=OptType.Observer) @AttrRef("phoneNum")
    public String getPhoneNum() {
        return this.phoneNum;
    }
    
    // @effects return <tt>address</tt>
    @DOpt(type=OptType.Observer) @AttrRef("address")
    public String getAddress() {
        return this.address;
    }

    /*
    @effects <pre>
    if id is valid 
        return true 
    else
        return false</pre>
    
    @effects <pre>
    if name is valid
        set this.name=name
        return true
    else
        return false</pre>
    */

    protected boolean validateId(int id) {
        if (id < MIN_ID || id > MAX_ID) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateName(String name) {
        if (name == null && name.length() == 0 && name.length() > LENGTH_NAME) {
            return false;
        }
        return true;
    }

    private boolean validatePhoneNum(String phoneNum) {
        if (phoneNum == null || phoneNum.length() == 0) {
            return false;
        }

        if (phoneNum.length() > LENGTH_PHONE_NUM) {
            return false;
        }
        return true;
    }

    private boolean validateAddress(String address) {
        if (address == null || address.length() == 0) {
            return false;
        }

        if (address.length() > LENGTH_ADDRESS) {
            return false;
        }
        return true;
    }

    public boolean repOk() {
        return validateId(id) && validateName(name) && validatePhoneNum(phoneNum) && validateAddress(address); 
    }
    
    @Override
    public String toString() {
        return "Student:<" + id + "," + name + "," + phoneNum + "," + address + ">";
    }

    @Override
    public boolean equals(Student o) {
        if (o == null && !(o instanceof Student)) {
            return false;
        } else {
            int yourID = ((Student) o).id;
            return yourID == id;
        }
    }
    
    @Override
    public int compareTo(Object o) throws NullPointerException, ClassCastException {
		if (o==null)
			throw new NullPointerException("Student.compareByName");
		else if (!(o instanceof Student))
			throw new ClassCastException("Student.compareByName: not a Student "+o);
		 
		Student s = (Student) o;
		return this.getName().compareTo(s.getName());
	}

    /* public boolean compareTo(Student o) {
        return this.name.compareTo(o.getName());
    } */
}