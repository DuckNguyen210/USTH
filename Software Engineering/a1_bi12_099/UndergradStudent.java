package a1_bi12_099;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/*
@overview
UndergradStudent is subclass of class Student 
 
@object
A typical UndergradStudent is c=<m,n,p,q>, where id(m), name(n), phoneNum(p), address(q).
 
@abstract_properties
min(id)= 1e5 /\ max(id)= 1e8
*/ 

public class UndergradStudent extends Student { 

    private static final int MIN_ID = 100000;
    private static final int MAX_ID = 100000000;

    public UndergradStudent(@AttrRef("id") int id, @AttrRef("name") String name, 
			@AttrRef("phoneNum") String phoneNum, @AttrRef("address") String address) throws NotPossibleException {
		super(id, name, phoneNum, address);
	}
    
    @Override
    public String toString() {
        return String.format("Undergraduate student: <%d, %s, %s, %s>",this.getId(),this.getName(),this.getPhoneNumber(),this.getAddress());
    }
    
    @Override
	@DomainConstraint(type="Integer", mutable=false, optional=false, min=MIN_ID, max=MAX_ID)
    private boolean validateID(int id) {
        if (id < MIN_ID || id > MAX_ID) {
            return false;
        } else {
            return true;
        }
    }
}