package ua.testing.registration_form.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.model.entity.Taxpayer;
import ua.testing.registration_form.controller.IRegController;
import ua.testing.registration_form.dto.NoteDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class RegService implements IRegController {

    @Autowired
    IRegService rs;

    @Override
    public void fromRegForm(NoteDTO note) throws RuntimeException {
        rs.saveNewTaxpayer(note.getName(), note.getLogin(),
                           note.getPassword());
    }

    @Override
    public List<NoteDTO> getAllTaxpayers() {
        List<Taxpayer> taxpayerList = rs.getAllTaxpayers();
        List<NoteDTO> noteDTOList = new ArrayList<>();
        IntStream.range(0, taxpayerList.size()).forEach(i ->
            noteDTOList.add(NoteDTO.builder()
                       .name(taxpayerList.get(i).getName())
                       .login(taxpayerList.get(i).getLogin())
                       .password(taxpayerList.get(i).getPassword())
                       .build()));
        return noteDTOList;
    }
}
