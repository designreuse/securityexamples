package at.scch.patientservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.scch.patientservice.entity.Patient;
import at.scch.patientservice.exception.NotFoundException;

@Service
public class PatientServiceImpl implements PatientService {
	
	private List<Patient> patients;
	
	@Autowired
	public PatientServiceImpl() {
		patients = new ArrayList<>();
		patients.add(new Patient("Marcel", "Lange", "0676 640 57 77", 123401011990L, "A+", "Gartenweg 39, 4212 Albingdorf", "Male"));
		patients.add(new Patient("Julia", "Nach", "0699 283 30 07", 123501011990L, "B+", "Huttenstrasse 99, 9654 TUFFBAD", "Female"));
		patients.add(new Patient("Alexander", "Baumgaertner", "0699 485 96 98", 123601011990L, "0+", "Aspernstrasse 61, 5282 SCHEUHUB", "Male"));
		
	}

	@Override
	public Iterable<Patient> getAllPatients() {
		return patients;
	}

	@Override
	public Patient getPatient(Long svnr) {
		for(Patient p:patients) {
			if(p.getSvnr().equals(svnr)) {
				return p;
			}
		}
		throw new NotFoundException();
	}

}
