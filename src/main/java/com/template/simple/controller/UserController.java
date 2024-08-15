package com.template.simple.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.template.simple.common.entity.PageData;
import com.template.simple.common.entity.request.ReqUserParam;
import com.template.simple.common.entity.response.RespUserVo;
import com.template.simple.entity.User;
import com.template.simple.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器", value = "用户控制器")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping()
    @ApiOperation("列表")
    public List<RespUserVo> list() {
        List<User> result = userService.list();
        List<RespUserVo> list = Lists.newArrayList();
        if (CollUtil.isNotEmpty(result)) {
            for (User user : result) {
                RespUserVo vo = new RespUserVo();
                BeanUtil.copyProperties(user, vo);
                list.add(vo);
            }
        }

        return list;
    }

    @GetMapping("/page")
    @ApiOperation("分页列表")
    public PageData<RespUserVo> page(@ApiParam(value = "页码", defaultValue = "1") @RequestParam Integer current,
                                     @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam Integer size) {
        Page<User> page = new Page<>(current, size);
        userService.page(page);

        List<RespUserVo> list = Lists.newArrayList();
        if (CollUtil.isNotEmpty(page.getRecords())) {
            for (User user : page.getRecords()) {
                RespUserVo vo = new RespUserVo();
                BeanUtil.copyProperties(user, vo);
                list.add(vo);
            }
        }

        return PageData.of(list, current, size, page.getTotal());
    }

    @GetMapping("/{id}")
    @ApiOperation("获取用户信息")
    public RespUserVo get(@PathVariable Long id) {
        User user = (User) redisTemplate.opsForValue().get(id.toString());
        if (ObjUtil.isEmpty(user)) {
            user = userService.getById(id);
            redisTemplate.opsForValue().set(id.toString(), user);
        }
        RespUserVo vo = new RespUserVo();
        BeanUtil.copyProperties(user, vo);

        return vo;
    }

    @PostMapping
    @ApiOperation("新增")
    public void add(@RequestBody ReqUserParam param) {
        User user = new User();
        BeanUtil.copyProperties(param, user);
        userService.save(user);
        redisTemplate.opsForValue().set(user.getId().toString(), user);
    }

    @PutMapping
    @ApiOperation("编辑")
    public void edit(@RequestBody ReqUserParam param) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, param.getId())
                .set(User::getName, param.getName());
        userService.update(updateWrapper);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public void del(@PathVariable Long id) {
        userService.removeById(id);
        redisTemplate.opsForValue().getAndDelete(id.toString());
    }

}
