package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.File_comparator;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class File_comparatorController {
    File_comparator fc;

    File_comparatorController()
    {
        fc = new File_comparator();
    }



    @GetMapping("/merge_files_dir")
    public void merge_files_dir(@RequestParam(value = "file1_dir") String file1,
                            @RequestParam(value = "file2_dir") String file2,
                            @RequestParam(value = "output_file_dir") String output)
    {
        fc.merge_files(file1,file2,output);
    }

    //@GetMapping("/merge_files")
    @RequestMapping(
            value ="merge_files", method = POST)
    public String merge_files(@RequestParam(value = "file1") String file1,
                            @RequestParam(value = "file2") String file2)
    {
        return fc.merge_strings_lines(file1,file2);
    }
}
