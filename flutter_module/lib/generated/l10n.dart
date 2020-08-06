// GENERATED CODE - DO NOT MODIFY BY HAND
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'intl/messages_all.dart';

// **************************************************************************
// Generator: Flutter Intl IDE plugin
// Made by Localizely
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, lines_longer_than_80_chars

class S {
  S();
  
  static S current;
  
  static const AppLocalizationDelegate delegate =
    AppLocalizationDelegate();

  static Future<S> load(Locale locale) {
    final name = (locale.countryCode?.isEmpty ?? false) ? locale.languageCode : locale.toString();
    final localeName = Intl.canonicalizedLocale(name); 
    return initializeMessages(localeName).then((_) {
      Intl.defaultLocale = localeName;
      S.current = S();
      
      return S.current;
    });
  } 

  static S of(BuildContext context) {
    return Localizations.of<S>(context, S);
  }

  /// `iEnglish`
  String get appName {
    return Intl.message(
      'iEnglish',
      name: 'appName',
      desc: '',
      args: [],
    );
  }

  /// `1.0.0`
  String get appCode {
    return Intl.message(
      '1.0.0',
      name: 'appCode',
      desc: '',
      args: [],
    );
  }

  /// `订货列表`
  String get order_good {
    return Intl.message(
      '订货列表',
      name: 'order_good',
      desc: '',
      args: [],
    );
  }

  /// `iEnglish服务商管理平台`
  String get home_title {
    return Intl.message(
      'iEnglish服务商管理平台',
      name: 'home_title',
      desc: '',
      args: [],
    );
  }

  /// `我的`
  String get tab_me {
    return Intl.message(
      '我的',
      name: 'tab_me',
      desc: '',
      args: [],
    );
  }

  /// `合伙人平台`
  String get tab_plateform {
    return Intl.message(
      '合伙人平台',
      name: 'tab_plateform',
      desc: '',
      args: [],
    );
  }

  /// `主页`
  String get tab_home {
    return Intl.message(
      '主页',
      name: 'tab_home',
      desc: '',
      args: [],
    );
  }
}

class AppLocalizationDelegate extends LocalizationsDelegate<S> {
  const AppLocalizationDelegate();

  List<Locale> get supportedLocales {
    return const <Locale>[
      Locale.fromSubtags(languageCode: 'zh'),
    ];
  }

  @override
  bool isSupported(Locale locale) => _isSupported(locale);
  @override
  Future<S> load(Locale locale) => S.load(locale);
  @override
  bool shouldReload(AppLocalizationDelegate old) => false;

  bool _isSupported(Locale locale) {
    if (locale != null) {
      for (var supportedLocale in supportedLocales) {
        if (supportedLocale.languageCode == locale.languageCode) {
          return true;
        }
      }
    }
    return false;
  }
}