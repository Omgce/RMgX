package com.op.asset;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.op.category.Category;
import com.op.category.CategoryRepository;

@Controller
public class AssetController {

	@Autowired
	private AssetRepository repo;

	@Autowired
	private CategoryRepository crepo;

	@GetMapping("/assets/new")
	public String showNewAssetForm(Model model) {
		List<Category> listCategories = crepo.findAll();

		model.addAttribute("listCategories", listCategories);
		model.addAttribute("asset", new Asset());
		return "asset_form";
	}

	@PostMapping("/assets/save")
	public String saveAsset(Asset asset) {
		repo.save(asset);
		return "redirect:/assets";
	}

	@GetMapping("/assets")
	public String listAssets(Model model) {
		List<Asset> listAssets = repo.findAll();
		model.addAttribute("listAssets", listAssets);
		return "assets";
	}

	@GetMapping("assets/edit/{id}")
	public String showEditAssetForm(@PathVariable("id") Integer id, Model model) {
		Asset asset = repo.findById(id).get();
		model.addAttribute("asset", asset);
		List<Category> listCategories = crepo.findAll();

		model.addAttribute("listCategories", listCategories);

		return "asset_form";
	}

	@GetMapping("assets/delete/{id}")
	public String deleteAsset(@PathVariable("id") Integer id, Model model) {
		repo.deleteById(id);
		return "redirect:/assets";
	}
}
