package ra.practice_rest_api.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.practice_rest_api.dto.request.CategoriesRequest;
import ra.practice_rest_api.dto.response.CategoriesResponse;
import ra.practice_rest_api.dto.response.CategoriesResponseActive;
import ra.practice_rest_api.mapper.CategoriesMapperActive;
import ra.practice_rest_api.mapper.CategoriesMapper;
import ra.practice_rest_api.model.Categories;
import ra.practice_rest_api.repository.CategoriesRepository;
import ra.practice_rest_api.service.CategoriesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImp implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private CategoriesMapper categoriesMapper;
    @Autowired
    private CategoriesMapperActive categoriesMapperActive;

    @Override
    public List<CategoriesResponse> find(int page, int size, String direction, String orderBy) {
        Pageable pageable;
        if (direction.equals("ASC")) {
            pageable = PageRequest.of(page,size, Sort.by(orderBy).ascending());
        } else {
            pageable = PageRequest.of(page,size,Sort.by(orderBy).descending());
        }
        List<Categories> listCatalog = categoriesRepository.findAll(pageable).getContent();
        return listCatalog.stream().map(categories -> categoriesMapper.mapperToResponse(categories))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriesResponse findById(long id) {
        Optional<Categories> otpCategories = categoriesRepository.findById(id);
        if (otpCategories.isPresent()) {
            return categoriesMapper.mapperToResponse(otpCategories.get());
        }
        return null;
    }

    @Override
    public CategoriesResponse save(CategoriesRequest categoriesRequest) {
        Categories categories = categoriesRepository.save(
                categoriesMapper.mapperToEntity(categoriesRequest));
        return categoriesMapper.mapperToResponse(categories);
    }

    @Override
    public CategoriesResponse update(CategoriesRequest categoriesRequest, long id) {
        boolean checkExist = categoriesRepository.existsById(id);
        if (checkExist) {
            Categories categories = categoriesRepository.save(
                    categoriesMapper.mapperToEntity(categoriesRequest));
            return categoriesMapper.mapperToResponse(categories);
        }
        return null;
    }

    @Override
    public CategoriesResponse updateStatus(long id) {
      Optional<Categories> otpCatalog = categoriesRepository.findById(id);
      if(otpCatalog.isPresent()) {
          Categories categoriesUpdateStatus = otpCatalog.get();
          // Cập nhật trạng thái là true - 0
          categoriesUpdateStatus.setStatus(true);
          categoriesRepository.save(categoriesUpdateStatus);
          return categoriesMapper.mapperToResponse(categoriesUpdateStatus);
      }
        return null;
    }

    @Override
    public List<CategoriesResponseActive> activeCategories() {
        List<Categories> listCatalog = categoriesRepository.findByStatus(true);
        return listCatalog.stream().map(categories -> categoriesMapperActive.mapperToResponse(categories))
                .collect(Collectors.toList());
    }

}
